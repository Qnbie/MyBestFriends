import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mybestfriends.Person
import com.example.mybestfriends.PhoneBook
import com.example.mybestfriends.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.person_tile.view.*
import layout.DetailActivity

class MainAdapter(val phoneBook: PhoneBook): RecyclerView.Adapter<MyViewHolder>() {

    override fun getItemCount(): Int {
        return phoneBook.results.size;
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val personTile = layoutInflater.inflate(R.layout.person_tile, parent, false)
        return MyViewHolder(personTile)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val person = phoneBook.results.get(position)
        holder.view.textView_PersoneName.text = person.name.toString()
        
        val picture = holder.view.imageView_Person
        Picasso.get().load(person.picture.medium).into(picture)

        holder.person = person
    }
}

class MyViewHolder(val view: View, var person: Person? = null) : RecyclerView.ViewHolder(view) {

    companion object{
        val DETAIL_NAME = "detailname"
        val DETAIL_EMAIL = "datailemail"
        val DETAIL_PHONE = "detailphone"
        val DETAIL_PICTURE = "detailpicture"
        val DETAIL_ADDRESS = "detailaddress"
        val DETAIL_NAT = "detailnat"
        val DETAIL_GENDER = "deailgender"
    }

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetailActivity::class.java )

            intent.putExtra(DETAIL_NAME, person?.name.toString())
            intent.putExtra(DETAIL_EMAIL, person?.email)
            intent.putExtra(DETAIL_PHONE, person?.phone)
            intent.putExtra(DETAIL_PICTURE, person?.picture?.large)
            intent.putExtra(DETAIL_ADDRESS, person?.location.toString())
            intent.putExtra(DETAIL_NAT, person?.nat)
            intent.putExtra(DETAIL_GENDER, person?.gender)


            view.context.startActivity(intent)
        }
    }

}