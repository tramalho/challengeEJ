package br.com.tramalho.enjoeitest.infraestructure

class URLBuilder {

    var baseURL: String = ""
    var crop: String = "fill"
    var gravity: String = "auto"
    var publicId: String = ""
    var width: Int = 0
    var height: Int = 0

    fun build() = "${baseURL}/c_${crop},g_${gravity},w_${width},h_${height}/${publicId}.jpg"
}
