import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.ankilightapp.model.Card

@Dao
interface CardDao {
    @Query("SELECT * FROM cards")
    fun getAllCards(): LiveData<List<Card>>

    @Query("SELECT * FROM cards WHERE id = :cardId LIMIT 1")
    fun getCardById(cardId: Long): LiveData<Card>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Card): Long

    @Update
    suspend fun update(card: Card): Int

    @Delete
    suspend fun delete(card: Card): Int
}
