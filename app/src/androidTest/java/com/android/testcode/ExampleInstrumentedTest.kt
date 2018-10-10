package com.android.testcode

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.CoreMatchers.allOf
import android.support.test.rule.ActivityTestRule
import android.view.View
import com.android.testcode.features.RegisterActivity
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import android.support.design.widget.TextInputLayout
import org.hamcrest.Description
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.Espresso.onView
import android.support.v7.widget.RecyclerView
import android.support.annotation.IdRes




/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Rule @JvmField
    val mActivityRule = ActivityTestRule(RegisterActivity::class.java)

    @Test
    fun testRegisterUser_Success() {

        val emailAddress = "firstname.lastname@g.com"
        val password = "Password1234"

        //find the email address edit text and type in the email address
        onView(withId(R.id.userName)).perform(typeText(emailAddress), closeSoftKeyboard())

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard())

        //click the signup button
        onView(withId(R.id.register)).perform(click())

        //check that we can see the success screen with success message
        val successString = "Result"//InstrumentationRegistry.getTargetContext().getString("TestCode")
        onView(withId(R.id.result)).check(matches(allOf(withText(successString), isDisplayed())))
    }

    @Test
    fun testRegisterUser_InvalidUserName() {

        val emailAddress = "firstname.lag.com"
        val password = "Password1234"

        //find the email address edit text and type in the email address
        onView(withId(R.id.userName)).perform(typeText(emailAddress), closeSoftKeyboard())

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard())

        //click the signup button
        onView(withId(R.id.register)).perform(click())

        //check that we can see the success screen with success message
        val errorString = "Not a valid email address!"

        onView(withId(R.id.userNameWrapper)).check(matches(hasTextInputLayoutErrorText(errorString)))
    }

    @Test
    fun testRegisterUser_InvalidPasswordNonAlphaNumeric() {

        val emailAddress = "firstname.l@ag.com"
        val password = "Pass=word1234"

        //find the email address edit text and type in the email address
        onView(withId(R.id.userName)).perform(typeText(emailAddress), closeSoftKeyboard())

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard())

        //click the signup button
        onView(withId(R.id.register)).perform(click())

        //check that we can see the success screen with success message
        val errorString = "Not a valid password!"

        onView(withId(R.id.passwordWrapper)).check(matches(hasTextInputLayoutErrorText(errorString)))
    }

    @Test
    fun testRegisterUser_InvalidPasswordLessThan5Chars() {

        val emailAddress = "firstname.l@ag.com"
        val password = "Pas"

        //find the email address edit text and type in the email address
        onView(withId(R.id.userName)).perform(typeText(emailAddress), closeSoftKeyboard())

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard())

        //click the signup button
        onView(withId(R.id.register)).perform(click())

        //check that we can see the success screen with success message
        val errorString = "Not a valid password!"

        onView(withId(R.id.passwordWrapper)).check(matches(hasTextInputLayoutErrorText(errorString)))
    }

    @Test
    fun testRegisterUser_InvalidPasswordSequenceFollowedBySameSequence() {

        val emailAddress = "firstname.l@ag.com"
        val password = "1Pass1Pass"

        //find the email address edit text and type in the email address
        onView(withId(R.id.userName)).perform(typeText(emailAddress), closeSoftKeyboard())

        //find the password edit text and type in the password
        onView(withId(R.id.password)).perform(typeText(password), closeSoftKeyboard())

        //click the signup button
        onView(withId(R.id.register)).perform(click())

        //check that we can see the success screen with success message
        val errorString = "Not a valid password!"

        onView(withId(R.id.passwordWrapper)).check(matches(hasTextInputLayoutErrorText(errorString)))
    }


    fun hasTextInputLayoutErrorText(expectedErrorText: String): Matcher<View> {
        return object : TypeSafeMatcher<View>() {


            public override fun matchesSafely(view: View): Boolean {
                if (view !is TextInputLayout) {
                    return false
                }

                val error = view.error ?: return false

                val hint = error.toString()

                return expectedErrorText.equals(hint)
            }

            override fun describeTo(description: Description?) {

            }
        }
    }

}