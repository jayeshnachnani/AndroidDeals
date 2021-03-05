package com.example.android.marsrealestate.models
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


/*class Tutorial(
        val title: String,
        val author: String,
        val categories: List<String>
) {
    override fun toString(): String {
        return "Category [title: ${this.title}, author: ${this.author}, categories: ${this.categories}]"
    }
}*/
@Parcelize
@Entity (tableName = "FIXED_INCOME_DEALS")
data class Deal (

        @PrimaryKey(autoGenerate = false)
        val fixedIncomeDealId: Long,
        @ColumnInfo(name = "deal_Name")
        val dealName: String,
        @ColumnInfo(name = "interest_Rate")
        val interestRate: Long
) : Parcelable


