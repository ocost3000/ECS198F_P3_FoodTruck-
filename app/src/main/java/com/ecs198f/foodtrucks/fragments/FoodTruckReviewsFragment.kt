package com.ecs198f.foodtrucks.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.ecs198f.foodtrucks.MainActivity
import com.ecs198f.foodtrucks.PostReviewRequest
import com.ecs198f.foodtrucks.databinding.FragmentFoodTruckReviewsBinding
import com.ecs198f.foodtrucks.models.FoodReview
import com.ecs198f.foodtrucks.adapters.FoodReviewListRecyclerViewAdapter
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FoodTruckReviewsFragment(val name: String, val id: String) : Fragment() {
    private lateinit var binding: FragmentFoodTruckReviewsBinding
    private var account: GoogleSignInAccount? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodTruckReviewsBinding.inflate(inflater, container, false)
        val recyclerViewAdapter = FoodReviewListRecyclerViewAdapter(listOf())

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .requestIdToken("699423245763-insnbbp034ep600msiqfan5g0b2pau67.apps.googleusercontent.com")
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(requireActivity() as MainActivity, gso)

        binding.reviewSignInBttn.setOnClickListener {
            val signInIntent = mGoogleSignInClient.signInIntent
            startActivityForResult(signInIntent, 9001)
        }

        binding.postButton.setOnClickListener {
            val request = PostReviewRequest(binding.reviewInputBox.text.toString(), listOf<String>())
            postReviews(recyclerViewAdapter, request)
        }

        binding.apply {
            foodReviewListRecyclerView.apply {
                adapter = recyclerViewAdapter
                layoutManager = LinearLayoutManager(context)
            }
        }

        getReviews(recyclerViewAdapter)

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        account = GoogleSignIn.getLastSignedInAccount(requireActivity() as MainActivity)

        if (account == null) {
            binding.postButton.isVisible = false
            binding.reviewInputBox.isVisible = false
            binding.reviewSignInBttn.isVisible = true
        } else {
            binding.postButton.isVisible = true
            binding.reviewInputBox.isVisible = true
            binding.reviewSignInBttn.isVisible = false
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 9001) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            account = completedTask.getResult(ApiException::class.java)
            binding.postButton.isVisible = true
            binding.reviewInputBox.isVisible = true
            binding.reviewSignInBttn.isVisible = false
        } catch (e: ApiException) {
            print("signInResult:failed code=" + e.statusCode)
            binding.postButton.isVisible = false
            binding.reviewInputBox.isVisible = false
            binding.reviewSignInBttn.isVisible = true
        }
    }

    private fun getReviews(recyclerViewAdapter: FoodReviewListRecyclerViewAdapter) {
        (requireActivity() as MainActivity).apply {
            foodTruckService.listFoodReviews(this@FoodTruckReviewsFragment.id).enqueue(object :
                Callback<List<FoodReview>> {
                override fun onResponse(
                    call: Call<List<FoodReview>>,
                    response: Response<List<FoodReview>>
                ) {
                    recyclerViewAdapter.updateReviews(response.body()!!)
                }

                override fun onFailure(call: Call<List<FoodReview>>, t: Throwable) {
                    recyclerViewAdapter.updateReviews(listOf())
                }
            })
        }
    }

    private fun postReviews(recyclerViewAdapter: FoodReviewListRecyclerViewAdapter, request: PostReviewRequest) {
        (requireActivity() as MainActivity).apply {
            Log.d("Tag", account!!.idToken.toString())
            foodTruckService.postFoodReviews(this@FoodTruckReviewsFragment.id, request, "Bearer " + account!!.idToken.toString()).enqueue(object :
                Callback<Unit> {
                override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                    Log.d("Tag", response.code().toString())
                    binding.reviewInputBox.setText("")
                    getReviews(recyclerViewAdapter)
                }

                override fun onFailure(call: Call<Unit>, t: Throwable) {
                    throw t
                }
            })
        }
    }
}