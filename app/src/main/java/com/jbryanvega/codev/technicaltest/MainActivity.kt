package com.jbryanvega.codev.technicaltest

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.data.repository.CoDevRepository
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.data.request.JobBody
import com.jbryanvega.codev.technicaltest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var repository: CoDevRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        //repository.getApplicant("8856ad1b-c365-4cce-84df-97e13a65834c")
        repository.getAllApplicants()
        repository.insertApplicant(ApplicantBody(UUID.randomUUID().toString(), "full name", "someemail@gmail.com"))
        repository.updateApplicant(ApplicantBody("ce268aea-512c-4092-b37c-360c7ab05d21", UUID.randomUUID().toString(), "someemail@gmail.com"))
        repository.deleteApplicant("81181108-5a4a-490b-9ea7-4a6eb9201550")
        repository.deleteApplicant("1181108-5a4a-490b-9ea7-4a6eb9201550")
        repository.deleteApplicant("259a4265-0e36-4ade-bbee-4aae77a1f6ed")
        repository.deleteApplicant("6e110126-ca04-449c-b60c-57e8127df79e")
        repository.deleteApplicant("10a87c7e-3f22-4ee2-ba1f-a5de9a5aa1ec")
        repository.deleteApplicant("03784699-c873-4982-9ccb-b9f881003035")
        repository.deleteApplicant("a4f30cc1-c9c5-4f3c-8384-ed47a8f55bb5")
        repository.deleteApplicant("e54fcf27-e589-46e0-916d-f57b092f6d87")
        repository.deleteApplicant("10a87c7e-3f22-4ee2-ba1f-a5de9a5aa1ec")

        repository.getJob("28d55e45-ea68-44f5-8031-7296ae60c9e7")
        repository.getJob("senior", 7)
        repository.getAllJobs()
        repository.insertJob(JobBody(UUID.randomUUID().toString(), 20, "senior recruiter", "senior recruitment specialist", 0))
        repository.updateJob(JobBody("93883b71-2df8-4fd6-8fe4-aa481add5730", 2, "senior recruiter " + UUID.randomUUID().toString(), "senior recruitment specialist " + UUID.randomUUID().toString(), 0))
        repository.deleteJob("0e756822-af05-4693-b9e8-7535b5ddd47a")

        //repository.applyJob()
    }
}