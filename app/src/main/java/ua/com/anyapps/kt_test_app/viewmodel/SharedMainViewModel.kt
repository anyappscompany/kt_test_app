package ua.com.anyapps.kt_test_app.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import ua.com.anyapps.kt_test_app.network.pojo.Books
import ua.com.anyapps.kt_test_app.network.pojo.Doc
import ua.com.anyapps.kt_test_app.repository.NetworkRepository
import ua.com.anyapps.kt_test_app.repository.NetworkRepositoryImpl
import ua.com.anyapps.kt_test_app.utils.NavigationPages

class SharedMainViewModel: ViewModel() {
    private val networkRepository: NetworkRepository = NetworkRepositoryImpl()

    private val books = MutableLiveData<Books>()
    private val networkRepositoryLoadError = MutableLiveData<Boolean>()
    private val repositoryLoading = MutableLiveData<Boolean>()

    private val navigation: MutableLiveData<NavigationPages> = MutableLiveData<NavigationPages>()
    private val selectedBook: MutableLiveData<Doc> = MutableLiveData<Doc>()

    init{
        // фрагмент приветствия по умолчанию
        navigation.postValue(NavigationPages.WELCOME)
    }
    fun getBooks(): LiveData<Books>{
        return books
    }

    fun getError(): LiveData<Boolean>{
        return networkRepositoryLoadError
    }

    fun getLoading(): LiveData<Boolean>{
        return repositoryLoading
    }

    // навигация для фрагментов
    fun getNavigationPage(): LiveData<NavigationPages>{
        return navigation
    }

    fun getSelectedBook(): LiveData<Doc>{
        return selectedBook
    }

    // поиск книги
    fun btnSearchClick(q: String){
        repositoryLoading.value = true
        networkRepository.getBooks(q).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            // показать результаты поиска
                            navigation.value = NavigationPages.SEARCH_RESULT
                            networkRepositoryLoadError.value = false
                            books.value = result
                            // отключить лоадер
                            repositoryLoading.value = false
                        },
                        { error ->
                            // загрузить фрагмент "ошибка"
                            navigation.value = NavigationPages.ERROR
                            networkRepositoryLoadError.value = true
                            repositoryLoading.postValue(false)
                        }
                )
    }

    fun onBookClick(doc: Doc){
        selectedBook.value = doc
        navigation.value = NavigationPages.DETAIL_BOOK
    }
}