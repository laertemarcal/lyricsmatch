package br.com.laertemarcal.lyricsmatch.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import br.com.laertemarcal.lyricsmatch.R;
import br.com.laertemarcal.lyricsmatch.fragments.ArtistsFragment;
import br.com.laertemarcal.lyricsmatch.fragments.LastLyricsFragment;
import br.com.laertemarcal.lyricsmatch.services.ArtistsService;


public class MainActivity extends AppCompatActivity {

    ArtistsFragment artistsFragment = new ArtistsFragment();
    ArtistsService artistsService = new ArtistsService(artistsFragment);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, artistsFragment)
                    .addToBackStack(null)
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setQueryHint(getResources().getString(R.string.search_hint));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Fragment frag = getSupportFragmentManager().findFragmentById(R.id.container);
                if (!(frag instanceof ArtistsFragment)) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, artistsFragment).commit();
                    getSupportFragmentManager().executePendingTransactions();
                }

                artistsFragment.getSpinner().setVisibility(View.VISIBLE);
                artistsFragment.getRecyclerView().setVisibility(View.INVISIBLE);

                artistsService.sendRequest(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.action_about:
                return true;
            case R.id.action_lastViewed:
                replaceFragment(new LastLyricsFragment());
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit();
    }
}
