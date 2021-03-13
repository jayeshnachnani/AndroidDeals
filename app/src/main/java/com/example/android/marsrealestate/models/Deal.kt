package com.example.android.marsrealestate.models
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity (tableName = "FIXED_INCOME_DEALS")
data class Deal (

        @PrimaryKey(autoGenerate = false)
        val fixedIncomeDealId: Long,
        @ColumnInfo(name = "deal_Name")
        val dealName: String,
        @ColumnInfo(name = "interest_Rate")
        val interestRate: Long,
        @ColumnInfo(name = "tenor")
        val tenor: Long,
        @ColumnInfo(name = "total_Amount")
        val totalAmount: Long,
        @ColumnInfo(name = "total_Tranches")
        val totalTranches: Long,
        @ColumnInfo(name = "remaining_Tranches")
        val remainingTranches: Long,
        @ColumnInfo(name = "rating")
        val rating: Long


) : Parcelable


