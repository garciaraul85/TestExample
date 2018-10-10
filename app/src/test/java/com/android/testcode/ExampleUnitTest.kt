package com.android.testcode

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.LiveData
import com.android.testcode.data.dao.UserDataSource
import com.android.testcode.data.model.User
import com.android.testcode.features.ui.listusers.ListUsersViewModel
import com.google.common.truth.Truth
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.junit.MockitoJUnit
import org.mockito.Mock
import android.arch.lifecycle.ViewModel
import com.android.testcode.features.ui.register.RegisterViewModel
import org.mockito.MockitoAnnotations
import org.junit.Before





/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    val rxSchedulerRule = RxSchedulerRule()

    @get:Rule
    val mockitoRule = MockitoJUnit.rule()

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    lateinit var classUnderTest: RegisterViewModel

    @Mock
    private val mDataModel: UserDataSource? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        classUnderTest = RegisterViewModel(mDataModel)
    }

    fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }


    @Test
    fun `testValidUserRegistration`() {
        val installationStatus = classUnderTest.responseLiveData.testObserver()

        classUnderTest.registerUser("userName@domain", "password123")
        //Note that this tests the real call to onFakeInstall(). It will take 3 seconds. It's possible to play with time in RX unit testing. See a next article maybe :P

        Truth.assert_()
                .that(installationStatus.observedValues.first()).isTrue()
    }

    @Test
    fun `testInvalidUserName`() {
        val installationStatus = classUnderTest.userNameErrorResponseLiveData.testObserver()

        classUnderTest.registerUser("userNamedomain", "password123")
        //Note that this tests the real call to onFakeInstall(). It will take 3 seconds. It's possible to play with time in RX unit testing. See a next article maybe :P

        Truth.assert_()
                .that(installationStatus.observedValues.first()).isTrue()
    }

    @Test
    fun `testInvalidPasswordLessThan5Char`() {
        val installationStatus = classUnderTest.passwordErrorResponseLiveData.testObserver()

        classUnderTest.registerUser("userNamedomain@a.com", "pas")
        //Note that this tests the real call to onFakeInstall(). It will take 3 seconds. It's possible to play with time in RX unit testing. See a next article maybe :P

        Truth.assert_()
                .that(installationStatus.observedValues.first()).isTrue()
    }

    @Test
    fun `testInvalidPasswordLessMoreThan12`() {
        val installationStatus = classUnderTest.passwordErrorResponseLiveData.testObserver()

        classUnderTest.registerUser("userNamedomain@a.com", "qazwsxedcrfv5tgb6yhn7u")
        //Note that this tests the real call to onFakeInstall(). It will take 3 seconds. It's possible to play with time in RX unit testing. See a next article maybe :P

        Truth.assert_()
                .that(installationStatus.observedValues.first()).isTrue()
    }

    @Test
    fun `testInvalidPasswordNonAlphaNumeric`() {
        val installationStatus = classUnderTest.passwordErrorResponseLiveData.testObserver()

        classUnderTest.registerUser("userNamedomain@a.com", "qz2$")
        //Note that this tests the real call to onFakeInstall(). It will take 3 seconds. It's possible to play with time in RX unit testing. See a next article maybe :P

        Truth.assert_()
                .that(installationStatus.observedValues.first()).isTrue()
    }

    @Test
    fun `testInvalidPasswordSequenceFollowedbySameSequence`() {
        val installationStatus = classUnderTest.passwordErrorResponseLiveData.testObserver()

        classUnderTest.registerUser("userNamedomain@a.com", "1hello1hello")
        //Note that this tests the real call to onFakeInstall(). It will take 3 seconds. It's possible to play with time in RX unit testing. See a next article maybe :P

        Truth.assert_()
                .that(installationStatus.observedValues.first()).isTrue()
    }


}