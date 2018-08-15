package test.com.recyclerviewitemresize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;


public class AdapterChangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.rv);

        ((SimpleItemAnimator) rv.getItemAnimator()).setSupportsChangeAnimations(true);
        rv.getItemAnimator().setChangeDuration(1500);
        rv.getItemAnimator().setMoveDuration(1500);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        final AdapterChangeAdapter adapter = new AdapterChangeAdapter();
        rv.setAdapter(adapter);
    }

}
