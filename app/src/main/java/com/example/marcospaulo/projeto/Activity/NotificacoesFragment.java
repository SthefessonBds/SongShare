package com.example.marcospaulo.projeto.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marcospaulo.projeto.R;

public class NotificacoesFragment extends Fragment {

    View view;
    private TabLayout tabLayout;
    private AppBarLayout appBarLayout;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notificacoes,container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        appBarLayout = (AppBarLayout) view.findViewById(R.id.frame_notificacoes);
        viewPager = (ViewPager) view.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new NotificacoesPessoaisFragment(), "Você");
        adapter.addFragment(new NotificacoesPublicasFragment(), "Seus amigos");
        adapter.addFragment(new ConvitesFragment(), "Convites");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return view;
    }

}
