package ua.com.anyapps.kt_test_app.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import ua.com.anyapps.kt_test_app.R
import ua.com.anyapps.kt_test_app.adapters.BookListRecyclerAdapter
import ua.com.anyapps.kt_test_app.databinding.FragmentSearchResultBinding
import ua.com.anyapps.kt_test_app.network.pojo.Doc
import ua.com.anyapps.kt_test_app.utils.SearchResultCellClickListener
import ua.com.anyapps.kt_test_app.viewmodel.SharedMainViewModel

class SearchResultFragment : Fragment() {

    private val sharedVM: SharedMainViewModel by activityViewModels()

    private var binding: FragmentSearchResultBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_result, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSearchResultBinding.bind(view)
        binding?.rvBookList?.layoutManager = LinearLayoutManager(requireContext())

        sharedVM.getBooks().observe(requireActivity()) {
            Log.d("debapp", "SharedTot:: ${it.numFound}")
            binding?.rvBookList?.adapter = BookListRecyclerAdapter(it, object: SearchResultCellClickListener{
                override fun onCellClickListener(doc: Doc) {
                    sharedVM.onBookClick(doc)
                }
            })
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            SearchResultFragment()
    }
}