import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.notes.Note

class NoteDataBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 2
        private const val TABLE_NAME = "ALLNOTES"
        private const val COLUMN_ID = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
        private const val COLUMN_COLOR = "color"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_TITLE TEXT, $COLUMN_CONTENT TEXT, $COLUMN_COLOR INTEGER)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 2) {
            val addColorColumn = "ALTER TABLE $TABLE_NAME ADD COLUMN $COLUMN_COLOR INTEGER DEFAULT 0"
            db?.execSQL(addColorColumn)
        }
    }

    fun insertTable(note: Note) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, note.headname)
        cv.put(COLUMN_CONTENT, note.textabout)
        cv.put(COLUMN_COLOR, note.color)
        db.insert(TABLE_NAME, null, cv)
        db.close()
    }

    fun getAllNotes(): List<Note> {
        val notesList = mutableListOf<Note>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            val color = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_COLOR))
            val note = Note(id, title, content, color)
            notesList.add(note)
        }
        cursor.close()
        db.close()
        return notesList
    }

    fun deleteNote(noteId: Int) {
        val db = writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(noteId.toString()))
        db.close()
    }

    fun updateTable(note: Note) {
        val db = writableDatabase
        val cv = ContentValues()
        cv.put(COLUMN_TITLE, note.headname)
        cv.put(COLUMN_CONTENT, note.textabout)
        cv.put(COLUMN_COLOR, note.color)
        db.update(TABLE_NAME, cv, "$COLUMN_ID=?", arrayOf(note.id.toString()))
        db.close()
    }
}
