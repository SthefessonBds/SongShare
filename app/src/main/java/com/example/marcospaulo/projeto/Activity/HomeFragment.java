package com.example.marcospaulo.projeto.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.marcospaulo.projeto.R;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment {

    private FrameLayout fl;
    private Button btnPlay;
    private SeekBar positionBar;
    public MediaPlayer mp2, mp3, mp4, mp5;
    private Button like, like1;
    int cont1 = 0;
    int cont2 = 0;
    private Toolbar toolbar, toolbarstatus;
    private TextView status, relapsedTimeLabel, remainingTimeLabel;
    int totalTime;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, 0);
        }

        like = (Button) view.findViewById(R.id.like);
        like1 = (Button) view.findViewById(R.id.like1);
        btnPlay = (Button) view.findViewById(R.id.btnPlay);
        relapsedTimeLabel = (TextView) view.findViewById(R.id.relapsedTimeLabel);
        remainingTimeLabel = (TextView) view.findViewById(R.id.remainingTimeLabel);
        fl = (FrameLayout) view.findViewById(R.id.fotopublicacao);

        toolbarstatus = view.findViewById(R.id.toolbar_satus);
        toolbar = view.findViewById(R.id.toolbar_inicio);
        toolbar.inflateMenu(R.menu.inicio_toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_mensagens:
                        Intent intent = new Intent(getContext(), TelaMensagens.class);
                        startActivity(intent);

                }

                return true;
            }
        });

        mp2 = MediaPlayer.create(getContext(), R.raw.uma_so_voz);
        mp2.setLooping(true);
        mp2.seekTo(0);
        totalTime = mp2.getDuration();

        positionBar = (SeekBar) view.findViewById(R.id.positionBar);
        positionBar.setMax(totalTime);
        positionBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(fromUser){
                    mp2.seekTo(progress);
                    positionBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        toolbarstatus.findViewById(R.id.tirarfoto).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tirarFoto();
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cont1 % 2 == 0) {
                    like.setBackgroundResource(R.drawable.ic_favorite_red_24dp);

                } else {
                    like.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
                cont1++;
            }
        });

        like1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cont2 % 2 == 0) {
                    like1.setBackgroundResource(R.drawable.ic_favorite_red_24dp);

                } else {
                    like1.setBackgroundResource(R.drawable.ic_favorite_black_24dp);
                }
                cont2++;
            }
        });

        btnPlay.setOnClickListener(play);

        status = (TextView) view.findViewById(R.id.text_status);

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                intent = new Intent(getContext(), Status.class);
                startActivity(intent);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mp2!=null){
                    try{
                        Message message = new Message();
                        message.what = mp2.getCurrentPosition();
                        handler.sendMessage(message);
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }

                }
            }
        }).start();



        return view;
    }


    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            positionBar.setProgress(currentPosition);

            String elapsedTime = createTimeLabel(currentPosition);
            relapsedTimeLabel.setText(elapsedTime);

            String remainingTime = createTimeLabel(totalTime - currentPosition);
            remainingTimeLabel.setText("- " + remainingTime);
        }
    };

    public String createTimeLabel(int time){
        String timeLabel = "";
        int min = time/1000/60;
        int sec = time/1000 % 60;

        timeLabel = min+":";
        if(sec < 10 ){
            timeLabel += "0";
        }
        timeLabel+=sec;

        return timeLabel;
    }


    public void tirarFoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == RESULT_OK)

            super.onActivityResult(requestCode, resultCode, data);
    }

    View.OnClickListener play = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mp2.isPlaying()) {
                mp2.pause();
                btnPlay.setBackgroundResource(R.drawable.play);
                fl.setBackgroundResource(R.drawable.victor);
            } else {
                mp2.start();
                fl.setBackgroundResource(R.drawable.victorescuro);
                btnPlay.setBackgroundResource(R.drawable.pause);
            }
        }
    };


    public void play2(View v) {
        if (mp2.isPlaying()) {
            mp2.pause();
            btnPlay.setBackgroundResource(R.drawable.play);
        } else {
            btnPlay.setBackgroundResource(R.drawable.pause);
            mp2.start();
        }
    }

    public void play3(View v) {
        if (mp3.isPlaying()) {
            mp3.pause();
        } else {
            mp3.start();
        }
    }


    public void play4(View v) {
        if (mp4.isPlaying()) {
            mp4.pause();
        } else {
            mp4.start();
        }
    }


    public void play5(View v) {
        if (mp5.isPlaying()) {
            mp5.pause();
        } else {
            mp5.start();
        }
    }


}
