package edu.cnm.deepdive.ironorimgtransform.controller;
package edu.cnm.deepdive.ironorimgtransform.controller;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import edu.cnm.deepdive.ironorimgtransform.R;
import edu.cnm.deepdive.ironorimgtransform.model.entity.Image;
import java.util.ArrayList;
import java.util.Set;

public class History extends AppCompatActivity {
  RecyclerView recyclerview;
  RecyclerView.Adapter adapter;

  ArrayList<String> userImages;

  LinearLayoutManager llm = new LinearLayoutManager(getActivity());

  @Override
  public View onCreate(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_history, container, false);

    recyclerview = view.findViewById(R.id.recycler_view);

    userImages = new ArrayList<>();
    for(int i = 0; i<10; ++i){
      userImages.add("Daniel # " + i);
    }

    recyclerview.setLayoutManager(llm);
    adapter = new HistoryAdapter(userImages);
    recyclerview.setAdapter(adapter);
    return view;
  }
}
