package com.example.studentbeans.viewmodel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.studentbeans.domain.MainViewModel
import com.example.studentbeans.domain.mainscreen.MainScreenEvent.*
import com.example.studentbeans.model.PhotoItem
import com.example.studentbeans.repository.NetworkRepository
import com.example.studentbeans.repository.mock.FakeNetworkRepoImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class MainViewModelTest {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var fakeNetworkRepo: NetworkRepository

    @Before
    fun setUp() {
        fakeNetworkRepo = FakeNetworkRepoImpl()
        mainViewModel = MainViewModel(fakeNetworkRepo)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `get photos from network`() = runBlocking {

        //Given
        val photos = mainViewModel.photos

        //When
        mainViewModel.onTriggerEvent(GetPhotosEvent)

        val expected = listOf(
            PhotoItem(
                albumId = 0,
                id = 0,
                thumbnailUrl = "",
                title = "",
                url = ""
            )
        )

        //Then
        assertThat(photos.value).isEqualTo(expected)
    }

    @Test
    fun `email gets changed`() = runBlocking {

        //Given
        val email = mainViewModel.email

        //When
        mainViewModel.onTriggerEvent(OnEmailChangedEvent("John@email.com"))

        val expected = "John@email.com"

        //Then
        assertThat(email.value).isEqualTo(expected)

    }

    @Test
    fun `password gets changed`() = runBlocking {

        //Given
        val password = mainViewModel.password

        //When
        mainViewModel.onTriggerEvent(OnPasswordChangedEvent("Password123"))

        val expected = "Password123"

        //Then
        assertThat(password.value).isEqualTo(expected)

    }

}