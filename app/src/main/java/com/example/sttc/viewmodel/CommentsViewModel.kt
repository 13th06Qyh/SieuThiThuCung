import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sttc.model.CommentData
import com.example.sttc.model.CommentWithUser
import com.example.sttc.model.Comments
import com.example.sttc.service.ApiService.apiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CommentsViewModel : ViewModel() {
    private val _comments = MutableStateFlow<List<CommentWithUser>>(emptyList())
    val comments: StateFlow<List<CommentWithUser>> = _comments
    private val _error = MutableStateFlow<String?>(null)

    fun fetchComments(blogId: Int) {
        viewModelScope.launch {
            val call: Call<CommentData> = apiService.getCommentsByBlogId(blogId)
            call.enqueue(object : Callback<CommentData> {
                override fun onResponse(
                    call: Call<CommentData>,
                    response: Response<CommentData>
                ) {
                    if (response.isSuccessful) {
                        val commentData = response.body()
                        _comments.value = commentData?.comments ?: emptyList()
                        Log.e("dscmt", commentData.toString())
                    } else {
                        Log.e("Loi cmt", "Error: ${response.code()}")
                    }
                }

                override fun onFailure(call: Call<CommentData>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                    t.printStackTrace()
                }
            })
        }
    }


    fun createCmt(comment: Comments, blogId: Int) {
        if (comment.noidungbl.isBlank()) {
            _error.value = "Comment content cannot be blank"
            return
        }
        viewModelScope.launch {
            apiService.createCmt(blogId, comment).enqueue(object : Callback<Comments> {
                override fun onResponse(
                    call: Call<Comments>,
                    response: Response<Comments>
                ) {
                    if (response.isSuccessful) {
                        fetchComments(blogId)

                    } else {
                        Log.e("API Error", "Error: ${response.code()}")
                        _error.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<Comments>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                    _error.value = "Error: ${t.message}"
                    t.printStackTrace()
                }
            })
        }
    }

    fun updateCmt (comment: Comments, blogId: Int, cmtId: Int) {
        if (comment.noidungbl.isBlank()) {
            _error.value = "Comment content cannot be blank"
            return
        }
        viewModelScope.launch {
            apiService.updateCmt(cmtId, comment).enqueue(object : Callback<Comments> {
                override fun onResponse(
                    call: Call<Comments>,
                    response: Response<Comments>
                ) {
                    if (response.isSuccessful) {

                        fetchComments(blogId)
                        Log.e("dscmtupdate", response.body().toString())

                    } else {
                        Log.e("API Error", "Error: ${response.code()}")
                        _error.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<Comments>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                    _error.value = "Error: ${t.message}"
                    t.printStackTrace()
                }
            })
        }
    }
    fun deleteCmt( blogId: Int, cmtId: Int) {
        viewModelScope.launch {
            apiService.deleteCmt(cmtId).enqueue(object : Callback<Comments> {
                override fun onResponse(
                    call: Call<Comments>,
                    response: Response<Comments>
                ) {
                    if (response.isSuccessful) {
                        fetchComments(blogId)
                    } else {
                        Log.e("API Error", "Error: ${response.code()}")
                        _error.value = "Error: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<Comments>, t: Throwable) {
                    Log.e("API Error", "Error: ${t.message}")
                    _error.value = "Error: ${t.message}"
                    t.printStackTrace()
                }
            })
        }
    }
}

