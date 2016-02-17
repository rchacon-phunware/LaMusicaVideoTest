package com.lamusica.lamusicavideo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lamusica.lamusicavideo.exoplayer.PlayerActivity;
import com.lamusica.lamusicavideo.jw.JWActivity;

import java.util.ArrayList;

public class ItemListFragment extends ListFragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_PLAYER = "section_number";

    public final static int EXO_PLAYER = 0;
    public final static int JW_PLAYER = 1;

    private int mCurrentPlayer = -1;


    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ItemListFragment newInstance(int player) {
        ItemListFragment fragment = new ItemListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PLAYER, player);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // TODO: replace with a real list adapter.
        setListAdapter(new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                TestData.URL_NAMES));
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCurrentPlayer = getArguments().getInt(ARG_PLAYER);
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);
        String url = TestData.URL_DATA[position];

        Intent mpdIntent = null;
        switch (mCurrentPlayer) {
            case EXO_PLAYER:
                mpdIntent = new Intent(this.getContext(), PlayerActivity.class)
                        .setData(Uri.parse(url))
                        .putExtra(PlayerActivity.CONTENT_ID_EXTRA, PlayerActivity.TYPE_HLS)
                        .putExtra(PlayerActivity.CONTENT_TYPE_EXTRA, PlayerActivity.TYPE_HLS);

                break;
            case JW_PLAYER:
                mpdIntent = new Intent(this.getContext(), JWActivity.class)
                        .setData(Uri.parse(url));
                mpdIntent.putExtra("AD",position == 11);
                break;
        }

        startActivity(mpdIntent);
    }
}
