package com.zj.relation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{
    final Resident me = new Resident("自己", 30, Resident.MALE);
    final Resident fa = new Resident("父", 60, Resident.MALE);
    final Resident ma = new Resident("母", 60, Resident.FEMALE);
    Resident gFa = new Resident("祖父", 80, Resident.MALE);
    Resident gMa = new Resident("祖母", 60, Resident.FEMALE);
    final Resident unB = new Resident("伯", 61, Resident.MALE);
    final Resident unS = new Resident("叔", 59, Resident.MALE);
    final Resident unSonB = new Resident("堂哥", 31, Resident.MALE);
    final Resident unSonS = new Resident("堂弟", 29, Resident.MALE);
    final Resident unDauB = new Resident("堂姐", 31, Resident.FEMALE);
    final Resident unDauS = new Resident("堂妹", 29, Resident.FEMALE);
    final Resident gFaDau = new Resident("姑姑", 60, Resident.FEMALE);
    Resident maFa = new Resident("外祖父", 60, Resident.MALE);
    Resident maMa = new Resident("外祖母", 60, Resident.FEMALE);
    final Resident maFaS = new Resident("舅舅", 60, Resident.MALE);
    final Resident maFaDau = new Resident("姨", 60, Resident.FEMALE);
    Resident maFaSonSonB = new Resident("表哥", 31, Resident.MALE);
    Resident maFaSonSonS = new Resident("表弟", 29, Resident.MALE);
    final Resident faSonS = new Resident("弟", 29, Resident.MALE);
    final Resident faSonB = new Resident("哥", 31, Resident.MALE);
    final Resident faDauS = new Resident("妹", 29, Resident.FEMALE);
    final Resident faDauB = new Resident("姐", 31, Resident.FEMALE);
    Resident faDauSon = new Resident("甥", 10, Resident.MALE);
    Resident faDauDau = new Resident("甥女", 10, Resident.FEMALE);
    Resident faSonSon = new Resident("侄", 10, Resident.MALE);
    Resident faSonDau = new Resident("侄女", 10, Resident.FEMALE);
    Resident wife = new Resident("妻子", 30, Resident.FEMALE);
    final Resident son = new Resident("儿子", 6, Resident.MALE);
    final Resident son1 = new Resident("儿子1", 7, Resident.MALE);
    final Resident dau = new Resident("女儿", 6, Resident.FEMALE);
    final Resident sonSon = new Resident("孙子", 1, Resident.MALE);
    final Resident dauSon = new Resident("外孙", 1, Resident.MALE);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initResidentRelation();
        initView();
    }

    private void initView()
    {
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.btn1:
                RelationBean relationBean = RelationFunc.getRelation(me, unDauS);
                ((TextView) findViewById(R.id.tv1)).setText(Integer.toBinaryString(relationBean.getRelation()));
                break;
            case R.id.btn2:
                ((TextView) findViewById(R.id.tv2)).setText(Boolean.toString(RelationFunc.isDirectRelation(me,fa)));
                break;
            case R.id.btn3:
                ((TextView) findViewById(R.id.tv3)).setText(Boolean.toString(RelationFunc.isSideRelation(me,fa)));
                break;
            case R.id.btn4:
                List<Resident> list = RelationFunc.getAllDirectResident(me);
                StringBuilder stringBuilder = new StringBuilder();
                for (Resident resident : list)
                {
                    stringBuilder.append(resident.getName()).append("|");
                }
                ((TextView) findViewById(R.id.tv4)).setText(stringBuilder.toString());
                break;
            case R.id.btn5:
                List<Resident> list1 = RelationFunc.getAllSideRelation(me);
                StringBuilder stringBuilder1 = new StringBuilder();
                for (Resident resident : list1)
                {
                    stringBuilder1.append(resident.getName()).append("|");
                }
                ((TextView) findViewById(R.id.tv5)).setText(stringBuilder1.toString());
                break;
            case R.id.btn6:
                List<Resident> list2 = RelationFunc.getResidentByRelation(me, RelationType.FATHER_FATHER_SON_SON);
                StringBuilder stringBuilder2 = new StringBuilder();
                for (Resident resident : list2)
                {
                    stringBuilder2.append(resident.getName()).append("|");
                }
                ((TextView) findViewById(R.id.tv6)).setText(stringBuilder2.toString());
                break;
            case R.id.btn7:
                ((TextView) findViewById(R.id.tv7)).setText(Boolean.toString(RelationFunc.isRelation(me, maFaSonSonB, RelationType.MOTHER_FATHER_SON_SON)));
                break;
        }

    }

    private void initResidentRelation()
    {
        me.setFather(fa);
        me.setMother(ma);
        me.setCouple(wife);
        me.setSonList(new ArrayList<Resident>(){{add(son); add(son1);}});
        me.setDaughterList(new ArrayList<Resident>(){{add(dau);}});

        fa.setFather(gFa);
        fa.setMother(gMa);
        fa.setCouple(ma);
        fa.setSonList(new ArrayList<Resident>(){{add(me); add(faSonS); add(faSonB);}});
        fa.setDaughterList(new ArrayList<Resident>(){{add(faDauB); add(faDauS);}});

        faDauB.setFather(fa);
        faDauB.setMother(ma);
        faDauB.setSonList(new ArrayList<Resident>(){{add(faDauSon);}});
        faDauB.setDaughterList(new ArrayList<Resident>(){{add(faDauDau);}});

        faSonB.setFather(fa);
        faSonB.setMother(ma);
        faSonB.setSonList(new ArrayList<Resident>(){{add(faSonSon);}});
        faSonB.setDaughterList(new ArrayList<Resident>(){{add(faSonDau);}});

        gFa.setSonList(new ArrayList<Resident>(){{add(fa); add(unB); add(unS);}});
        gFa.setDaughterList(new ArrayList<Resident>(){{add(gFaDau);}});
        gMa.setCouple(gFa);
        gMa.setSonList(new ArrayList<Resident>(){{add(fa);}});

        unB.setFather(gFa);
        unB.setMother(gMa);
        unB.setSonList(new ArrayList<Resident>(){{add(unSonB); add(unSonS);}});
        unB.setDaughterList(new ArrayList<Resident>(){{add(unDauB); add(unDauS);}});

        ma.setMother(maMa);
        ma.setFather(maFa);
        ma.setSonList(new ArrayList<Resident>(){{add(me); add(faSonS); add(faSonB);}});
        ma.setDaughterList(new ArrayList<Resident>(){{add(faDauB); add(faDauS);}});
        ma.setCouple(fa);

        maFa.setCouple(maMa);
        maFa.setSonList(new ArrayList<Resident>(){{add(maFaS);}});
        maFa.setDaughterList(new ArrayList<Resident>(){{add(maFaDau); add(ma);}});

        maFaS.setFather(maFa);
        maFaS.setMother(maMa);
        maFaS.setSonList(new ArrayList<Resident>(){{add(maFaSonSonB); add(maFaSonSonS);}});

        maMa.setCouple(maFa);
        maMa.setSonList(new ArrayList<Resident>(){{add(maFaS);}});
        maMa.setDaughterList(new ArrayList<Resident>(){{add(maFaDau); add(ma);}});

        wife.setCouple(me);
        wife.setSonList(new ArrayList<Resident>(){{add(son); add(son1);}});
        wife.setDaughterList(new ArrayList<Resident>(){{add(dau);}});

        son.setMother(wife);
        son.setFather(me);
        son.setSonList(new ArrayList<Resident>(){{add(sonSon);}});

        son1.setMother(wife);
        son1.setFather(me);

        dau.setMother(wife);
        dau.setFather(me);
        dau.setSonList(new ArrayList<Resident>(){{add(dauSon);}});
    }

}