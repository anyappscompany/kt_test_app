package ua.com.anyapps.kt_test_app.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import ua.com.anyapps.kt_test_app.R
import ua.com.anyapps.kt_test_app.adapters.BookListRecyclerAdapter
import ua.com.anyapps.kt_test_app.databinding.FragmentDetailBookBinding
import ua.com.anyapps.kt_test_app.databinding.FragmentSearchResultBinding
import ua.com.anyapps.kt_test_app.network.pojo.Doc
import ua.com.anyapps.kt_test_app.utils.SearchResultCellClickListener
import ua.com.anyapps.kt_test_app.viewmodel.SharedMainViewModel


class DetailBookFragment : Fragment() {

    private val sharedVM: SharedMainViewModel by activityViewModels()

    private var binding: FragmentDetailBookBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_book, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBookBinding.bind(view)
        initObservers()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    fun initObservers(){
        sharedVM.getSelectedBook().observe(requireActivity()) {
            binding?.tvTitle?.text = it.title
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DetailBookFragment()
    }
}