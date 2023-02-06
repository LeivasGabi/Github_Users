package com.example.githubusers.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.githubusers.databinding.FragmentSearchBinding
import com.example.githubusers.model.User
import com.example.githubusers.network.EndPointPath
import com.example.githubusers.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val  binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonClicked()
    }

    private fun setButtonClicked() {
        binding.buttonSearch.setOnClickListener {
            getData()
        }
    }

    private fun getData() {
        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPath = retrofitBase.create(EndPointPath::class.java)
        val callback = endPointPath.getUsers(binding.editTextSearch.text.toString())

        callback.enqueue(object: Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                Toast.makeText(requireContext(), "Não funciona", Toast.LENGTH_LONG).show()
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                response.body()?.let {
                    var action = SearchFragmentDirections.actionHomeFragmentToRepositoryListFragment(
                        it
                    )
                    findNavController().navigate(action)
                }
            }
        })
    }

}