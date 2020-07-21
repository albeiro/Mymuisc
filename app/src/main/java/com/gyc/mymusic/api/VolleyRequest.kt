import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.widget.ImageView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.gyc.mymusic.api.IResponseServer
import org.json.JSONObject

class VolleyRequest(context: Context?) {
    private val cxt = context
    private val queue: RequestQueue

    companion object {
        private var INSTANCE: VolleyRequest? = null
        fun getInstance(context: Context?) =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: VolleyRequest(context).also { INSTANCE = it }
            }
    }

    init {
        this.queue = Volley.newRequestQueue(cxt)
    }

    fun stringRequest(
        url: String,
        parametros: JSONObject?,
        headers: HashMap<String, String>?,
        iRespuesta: IResponseServer?
    ) {
        val stringRequest = object : StringRequest(Method.GET, url,
            Response.Listener<String> { respuesta ->
                Log.i("StringRequestSucces", parametros.toString())
                iRespuesta?.success(respuesta)
            }, Response.ErrorListener { error ->
                Log.i("StringRequestErrt", parametros.toString())
                iRespuesta?.error(error)

            }) {

            override fun getHeaders(): MutableMap<String, String> {
                if (headers != null) {
                    return headers
                }
                return super.getHeaders()
            }
        }
        this.queue.add(stringRequest)
    }


    fun jsonRequest(
        url: String,
        parametros: JSONObject?,
        headers: HashMap<String, String>?,
        iRespuesta: IResponseServer?
    ) {
        Log.i("JsonRequestURl", url)
        Log.i("he", headers.toString())
        val jsonObjReq = object : JsonObjectRequest(url, parametros,
            Response.Listener { response ->
                Log.i("JsonRequestSucces", response.toString())

                iRespuesta?.success(response)
            },
            Response.ErrorListener { error ->
                if (error.networkResponse != null) {
                    iRespuesta?.error(error)

                }
                var codigo :Int? = error?.networkResponse?.statusCode
                codigo = codigo ?: 404
                Log.i("JsonRequestError", "Eror: $codigo")
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                if (headers != null) {

                    return headers
                }
                return super.getHeaders()
            }
        }
        jsonObjReq.retryPolicy = DefaultRetryPolicy(
            DefaultRetryPolicy.DEFAULT_TIMEOUT_MS * 0,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )
        Log.i("jsonObjReq", "s $jsonObjReq")
        this.queue.add(jsonObjReq)

    }

    fun imgRequest(
        url: String,
        maxWidth: Int,
        maxHeight: Int,
        scaleType: ImageView.ScaleType,
        decodeConfig: Bitmap.Config,
        header: HashMap<String, String>?,
        iRespuesta: IResponseServer?
    ) {
        Log.i("imgRequestUrlBUsqueda", url)
        val response = ImageRequest(url,
            Response.Listener<Bitmap> { response ->

                iRespuesta?.success(response)
            }, maxWidth, maxHeight, scaleType, decodeConfig,
            Response.ErrorListener { error ->

                iRespuesta?.error(error)
            })
        this.queue.add(response)
    }
}