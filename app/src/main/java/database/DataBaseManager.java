package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import game.Game;
import user.Usuario;

import application.R;

public class DataBaseManager extends SQLiteOpenHelper {
    private static final String LOG = "DatabaseManager";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "GameSorterDB";
    private static final String TABLE_USERS = "Users";
    private static final String TABLE_GAMES = "Games";

    private static final String SQL_CREATE_USERS = "CREATE TABLE " + TABLE_USERS
            + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "email VARCHAR(15), "
            + "senha VARCHAR(14)"
            + ");";

    private static final String SQL_CREATE_GAMES = "CREATE TABLE " + TABLE_GAMES
            + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nome VARCHAR(15), "
            + "numeroJogadores INTEGER"
            + ");";


    private static final String SQL_DELETE_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS;
    private static final String SQL_DELETE_GAMES = "DROP TABLE IF EXISTS " + TABLE_GAMES;

    public DataBaseManager (Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_GAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_GAMES);
        onCreate(db);
    }

    public long createUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    public Usuario readUsuario(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "email", "senha"};
        String[] args = {String.valueOf(id)};

        Cursor data = db.query(TABLE_USERS, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Usuario u = new Usuario();
        u.setId(data.getInt(0));
        u.setEmail(data.getString(1));
        u.setSenha(data.getString(2));
        data.close();
        db.close();

        return u;
    }

    public long updateUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", usuario.getEmail());
        values.put("senha", usuario.getSenha());
        long rows = db.update(TABLE_USERS, values, "_id = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return rows;
    }

    public long deleteUsuario(Usuario usuario) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rows = db.delete(TABLE_USERS, "_id = ?", new String[]{String.valueOf(usuario.getId())});
        db.close();
        return rows;
    }

    public void readAllUsuarios (Context context, ListView lv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "email", "senha"};
        Cursor data = db.query(TABLE_USERS, columns, null, null, null,
                null, null);
        int[] to = {R.id.signInTextViewEmail, R.id.signInTextViewSenha};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.login_activity, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }

    //-----------------------------------------------------------------

    public long createGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", game.getNome());
        values.put("numeroJogadores", game.getNumeroJogadores());
        long id = db.insert(TABLE_GAMES, null, values);
        db.close();
        return id;
    }

    public Game readGame(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome", "numeroJogadores"};
        String[] args = {String.valueOf(id)};

        Cursor data = db.query(TABLE_GAMES, columns, "_id = ?", args, null,
                null, null);
        data.moveToFirst();
        Game g = new Game();
        g.setId(data.getInt(0));
        g.setNome(data.getString(1));
        g.setNumeroJogadores(data.getInt(2));
        data.close();
        db.close();

        return g;
    }

    public long updateGame(Game g) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nome", g.getNome());
        values.put("numeroJogadores", g.getNumeroJogadores());
        long rows = db.update(TABLE_GAMES, values, "_id = ?", new String[]{String.valueOf(g.getId())});
        db.close();
        return rows;
    }

    public long deleteGame(Game game) {
        SQLiteDatabase db = this.getWritableDatabase();
        long rows = db.delete(TABLE_GAMES, "_id = ?", new String[]{String.valueOf(game.getId())});
        db.close();
        return rows;
    }

    public void readAllGame (Context context, ListView lv) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = {"_id", "nome", "numeroJogadores"};
        Cursor data = db.query(TABLE_GAMES, columns, null, null, null,
                null, null);
        int[] to = {R.id.signInTextViewEmail, R.id.signInTextViewSenha};
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(context,
                R.layout.login_activity, data, columns, to, 0);
        lv.setAdapter(simpleCursorAdapter);
        db.close();
    }
}
