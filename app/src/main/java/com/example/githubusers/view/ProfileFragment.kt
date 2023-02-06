package com.example.githubusers.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.githubusers.R
import com.example.githubusers.adapter.RepositoryListAdapter
import com.example.githubusers.databinding.FragmentProfileBinding
import com.example.githubusers.model.Repository
import com.example.githubusers.network.EndPointPath
import com.example.githubusers.network.NetworkUtils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val args: ProfileFragmentArgs by navArgs()
    private lateinit var repositoryAdapter: RepositoryListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadView()
    }

    private fun loadView() {

        Glide.with(requireContext()).load(args.user?.avatarUrl).into(binding.imageView)

        binding.textViewName.text = args.user!!.name

        getData()
    }

    private fun getData() {
        val retrofitBase = NetworkUtils.getRetrofitInstance()
        val endPointPath = retrofitBase.create(EndPointPath::class.java)
        val callback = endPointPath.getRepos(args.user!!.login)

        val avatarImage = args.user?.avatarUrl?.let { endPointPath.getRepos(it) }

        callback.enqueue(object: Callback<List<Repository>> {
            override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                Toast.makeText(requireContext(), "Não funciona", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                response.body()?.let {
                    setAdapter(it)
                }
            }
        })
    }

    private fun setAdapter(listRepos: List<Repository>) {
        repositoryAdapter = RepositoryListAdapter(listRepos) {
            openGithub(it)
        }

        binding.recyclerView.apply {
            adapter = repositoryAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun openGithub(repository: Repository) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(repository.htmlUrl))
        startActivity(intent)
    }
}
