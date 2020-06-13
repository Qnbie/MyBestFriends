package layout

import MyViewHolder.Companion.DETAIL_ADDRESS
import MyViewHolder.Companion.DETAIL_EMAIL
import MyViewHolder.Companion.DETAIL_GENDER
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mybestfriends.R
import MyViewHolder.Companion.DETAIL_NAME
import MyViewHolder.Companion.DETAIL_NAT
import MyViewHolder.Companion.DETAIL_PHONE
import MyViewHolder.Companion.DETAIL_PICTURE
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detail_panel.*

class DetailActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.detail_panel)

        Picasso.get().load(intent.getStringExtra(DETAIL_PICTURE)).into(imageView_DetaiPicture)
        textView_NameValue.text = intent.getStringExtra(DETAIL_NAME)
        textView_EmailValue.text = intent.getStringExtra(DETAIL_EMAIL)
        textView_PhoneValue.text = intent.getStringExtra(DETAIL_PHONE)
        textView_AddressValue.text = intent.getStringExtra(DETAIL_ADDRESS)
        textView_NationValue.text = intent.getStringExtra(DETAIL_NAT)
        textView_GenderValue.text = intent.getStringExtra(DETAIL_GENDER)
    }
}