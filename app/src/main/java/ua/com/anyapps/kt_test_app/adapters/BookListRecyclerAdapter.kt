package ua.com.anyapps.kt_test_app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ua.com.anyapps.kt_test_app.R
import ua.com.anyapps.kt_test_app.network.pojo.Books
import ua.com.anyapps.kt_test_app.network.pojo.Doc
import ua.com.anyapps.kt_test_app.utils.SearchResultCellClickListener

class BookListRecyclerAdapter(private val books: Books, private val searchResultCellClickListener: SearchResultCellClickListener):
    RecyclerView.Adapter<BookListRecyclerAdapter.ViewHolder>() {
    var onItemClick: ((Doc) -> Unit)? = null
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView
        val tvDescription: TextView
        val ivBookCover: ImageView

        init {
            // Define click listener for the ViewHolder's View.
            tvTitle = view.findViewById(R.id.tvTitle)
            tvDescription = view.findViewById(R.id.tvDescription)
            ivBookCover = view.findViewById(R.id.ivBookCover)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_book_list, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.tvTitle.text = books.docs.get(position).title
        viewHolder.tvDescription.text = books.docs.get(position).title_suggest

        viewHolder.itemView.setOnClickListener {
            searchResultCellClickListener.onCellClickListener(books.docs.get(position))
        }

        books.docs.get(position).isbn?.get(0)?.let {
            Glide.with(viewHolder.ivBookCover.context).load("http://covers.openlibrary.org/b/isbn/${it}-M.jpg").placeholder(R.drawable.ic_launcher_background).into(viewHolder.ivBookCover)
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = books.docs.size

}
