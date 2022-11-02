package com.atom.android.bookshop

import android.content.Context
import android.content.SharedPreferences
import com.atom.android.bookshop.data.model.User
import com.atom.android.bookshop.data.repository.AccountRepository
import com.atom.android.bookshop.data.source.remote.IRequestCallback
import com.atom.android.bookshop.data.source.remote.ResponseObject
import com.atom.android.bookshop.ui.account.AccountContract
import com.atom.android.bookshop.ui.account.AccountPresenter
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class AccountPresenterTest {

    private val view = mockk<AccountContract.View>(relaxed = true)
    private val repository = mockk<AccountRepository>()
    private val presenter = AccountPresenter(repository)
    private val callback = slot<IRequestCallback<ResponseObject<User>>>()
    private val user = mockk<User>()
    private val token = ""
    private var context = mockk<Context>(relaxed = true)

    @Before
    fun setView() {
        presenter.setView(view)
    }

    @Test
    fun `get user success`() {
        val responseObject = ResponseObject<User>(true, "", user)
        every {
            repository.getUser(token, capture(callback))
        } answers {
            callback.captured.onSuccess(responseObject)
        }
        presenter.getUser(context)
        verify(exactly = 1) {
            view.getUserSuccess(responseObject.data as User)
        }
    }

    @Test
    fun `get user failed`() {
        val responseObject = ResponseObject<User>(true, "", user)
        every {
            repository.getUser(token, capture(callback))
        } answers {
            callback.captured.onFailed(responseObject.message)
        }
        presenter.getUser(context)
        verify(exactly = 1) {
            view.getUserFailed(responseObject.message)
        }
    }

    @Test
    fun `get instance object is unique`() {
        val presenter = AccountPresenter.getInstance(repository)
        val mPresenter = AccountPresenter.getInstance(repository)
        Assert.assertEquals(presenter, mPresenter)
    }
}
