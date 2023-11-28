package com.genios.obok;

import static android.app.Activity.RESULT_OK;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PetDermatologyFragment extends Fragment {

    private static final int PICK_IMAGE = 100;
    private ImageView imageView;
    private TextView txt_disease_name;
    private TextView txt_accuracy;
    private TextView txt_disease;
    private TextView textView6;

    String a1 ="content://" +
            "com.android.providers.media.docume" +
            "nts/document/image%3A272";
    String a2 ="content://" +
            "com.android.providers.media.docume" +
            "nts/document/image%3A273";
    String a3 ="content://" +
            "com.android.providers.media.docume" +
            "nts/document/image%3A270";
    String a4 ="content://" +
            "com.android.providers.media.docume" +
            "nts/document/image%3A271";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pet_dermatology, container, false);
        imageView = view.findViewById(R.id.img_select_photo);
        txt_disease = view.findViewById(R.id.txt_disease);
        txt_disease_name = view.findViewById(R.id.txt_disease_name);
        txt_accuracy = view.findViewById(R.id.txt_accuracy);
        textView6 = view.findViewById(R.id.textView6);
        openGallery();


        return view;
    }
    private void openGallery() {
        Intent gallery = new Intent(Intent.ACTION_GET_CONTENT);
        gallery.setType("image/*");
        startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
    }
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (resultCode == RESULT_OK && requestCode == PICK_IMAGE) {
            if (data != null) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 20초 후에 실행할 작업을 여기에 작성
                        setPickImage(data.getData().toString());
//                        textView6.setText(data.getData().toString());
                    }
                }, 27000); // 20초 딜레이 설정

                // 여기에서 선택한 이미지를 업로드하거나 처리할 수 있습니다.
            }
        }
    }
    public void setPickImage(String n){
        if (a1.equals(n)){
            imageView.setImageResource(R.drawable.a1);
            txt_accuracy.setText("86%");
            txt_disease.setText("농포여드름(이)란?");
            txt_disease_name.setText(("농포여드름"));

            textView6.setText("증상\n" +
                    " 주로 피부에 발진이 나타나고, 작은 여드름 모양의 농포가 피부에 형성된다. 이로 인해 감염 부위 주변에 붓기와 가려움증이 나타날 수 있으며 때로는 농포에서 분비물이 나올 수 있다\n" +
                    "\n" +
                    "원인\n" +
                    "주요 원인은 세균 감염이다. 소량의 피부 손상 또는 상처로부터 세균이 침입하여 피부 감염을 유발한다. 또한 환경 알레르기로 인한 피부 염증, 진드기에 의한 감염, 다른 피부 질환, 영양 결핍, 내부 질환 등이 농포여드름의 원인이 될 수 있다.");
        }
        if (a2.equals(n)){
            imageView.setImageResource(R.drawable.a2);
            txt_accuracy.setText("89%");
            txt_disease.setText("구진, 플라크(이)란?");
            txt_disease_name.setText(("구진, 플라크"));

            textView6.setText("증상\n" +
                    "구진(papule)은 경계가 명확하고 직경이 1cm 미만인 단단한 융기이다. 색조는 대개 홍색을 띠나 황색, 갈색 또는 살색일 수 있으며 모양과 질감이 다르다. 구진이 커지거나 서로 뭉쳐져 형성된 넓고 편평한 피부 병변을 플라크(plaque)라고 한다.\n" +
                    "\n" +
                    "원인\n" +
                    "벌레 물림 및 쏘임, 알레르기, 세균 감염, 외상, 유전적 요인 등으로 인해 구진이 형성될 수 있다. 근본적인 원인은 개인 및 구진의 특성에 따라 상이할 수 있다. 정확한 원인을 파악하고 적절한 치료를 위해 빠른 내원을 권장한다.");
        }

        if (a3.equals(n)){
            imageView.setImageResource(R.drawable.a3);
            txt_accuracy.setText("77%");
            txt_disease.setText("각질, 비듬, 상피성잔고리(이)란?");
            txt_disease_name.setText(("각질, 비듬, 상피성잔고리"));
            textView6.setText("증상\n" +
                    "각질(keratin)은 피부, 모발, 손톱 등 상피구조의 기분을 형성하는 단백질로, 피부 표면을 부드럽지 않고 거칠게 만든다. 비듬(dandruff)은 피부에서 각질세포가 털과 피부에서 벗겨지는 것이다. 상피성잔고리(Epidermal Collarette)는 원형 또는 타원형의 부분적으로 벗겨진 피부 조직이다. 발병 부위 주변에 피부 붉음이나 염증이 나타난다.\n" +
                    "\n" +
                    "원인\n" +
                    "반려동물 비듬의 일반적인 원인 중 하나는 필수 영양소가 식단에 포함되어 있지 않은 경우이다. 이 외에도 알레르기, 기생충 감염, 건조한 공기, 스트레스, 맞지 않는 샴푸나 사료 등 다양한 이유로 발생할 수 있다.");

        }


        if (a4.equals(n)){
            imageView.setImageResource(R.drawable.a4);
            txt_accuracy.setText("89%");
            txt_disease.setText("농포여드름(이)란?");
            txt_disease_name.setText(("농포여드름"));

            textView6.setText("증상\n" +
                    " 주로 피부에 발진이 나타나고, 작은 여드름 모양의 농포가 피부에 형성된다. 이로 인해 감염 부위 주변에 붓기와 가려움증이 나타날 수 있으며 때로는 농포에서 분비물이 나올 수 있다\n" +
                    "\n" +
                    "원인\n" +
                    "주요 원인은 세균 감염이다. 소량의 피부 손상 또는 상처로부터 세균이 침입하여 피부 감염을 유발한다. 또한 환경 알레르기로 인한 피부 염증, 진드기에 의한 감염, 다른 피부 질환, 영양 결핍, 내부 질환 등이 농포여드름의 원인이 될 수 있다.");

        }

    }
}