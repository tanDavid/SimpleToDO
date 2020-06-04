package sg.edu.rp.c346.id19002765.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText editTodo;
    Button btnAdd, btnClear, btnDelete;
    ListView lvTodo;
    Spinner spn;

    ArrayList<String> alTodo;
    ArrayAdapter aaTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTodo = findViewById(R.id.editTasks);
        btnAdd = findViewById(R.id.buttonAdd);
        btnClear = findViewById(R.id.buttonClear);
        lvTodo = findViewById(R.id.listViewToDo);
        spn = findViewById(R.id.spinner);

        btnDelete = findViewById(R.id.buttonDel);

        alTodo = new ArrayList<>();
        aaTodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alTodo);

        lvTodo.setAdapter(aaTodo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTodo = editTodo.getText().toString();
                alTodo.add(newTodo);
                aaTodo.notifyDataSetChanged();
                editTodo.setText(null);

            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alTodo.clear();
                aaTodo.notifyDataSetChanged();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(editTodo.getText().toString());

                if(alTodo.size() == 0){
                    Toast.makeText(getApplicationContext(), "You don't have any task to remove", Toast.LENGTH_SHORT).show();

                }

                if(pos >= alTodo.size()){
                    Toast.makeText(getApplicationContext(), "Wrong index number", Toast.LENGTH_SHORT).show();

                }
                else{
                    alTodo.remove(pos);
                    aaTodo.notifyDataSetChanged();
                }


            }
        });


        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        editTodo.setHint("Type in a new task here");
                        btnDelete.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;
                    case 1:
                        editTodo.setHint("Type in the index of the task to be removed");
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }
}
