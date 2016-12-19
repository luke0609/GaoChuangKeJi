package com.jiaokaokeji.gaochuangkeji.home.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.jiaokaokeji.gaochuangkeji.R;
import com.jiaokaokeji.gaochuangkeji.home.Activity.Adapter.ListViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.R.id.list;

public class ProblemActivity extends AppCompatActivity {
//常见问题
   private ListView summonerList;
    private ImageView back;
    private List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem);
        initData();
        initview();
    }
     private void initData() {
         Map<String, Object> map = new HashMap<String, Object>();
         map.put("problem", "天保学车是一款什么样的软件？");
         map.put("answer", "天保学车由APP、官网和公众号三部分组成，本软件的初衷是为了给学员提供便利、规范驾校管理流程。学员通过天保学车APP可以网上预约报名、考题练习以及连接教练车获取模拟成绩。");
         list.add(map);

         map = new HashMap<String, Object>();
         map.put("problem", "使用天保学车APP，能获得哪些便捷的服务？");
         map.put("answer", "天保学车专注于为学员打造更简单、最贴心的学车体验。" +
                 "\n1.报名信息提交，优先上门办理入学。" +
                 "\n2.个人学习进度和考试成绩便捷查询" +
                 "\n3.模拟考试更加全面");
         list.add(map);

         map = new HashMap<String, Object>();
         map.put("problem", "学车过程中遇到了问题，我该找谁？");
         map.put("answer", "学车过程中遇到的任何问题都可以随时向天保驾校的服务中心咨询或投诉");
         list.add(map);

         map = new HashMap<String, Object>();
         map.put("problem", "报考驾校需要准备哪些材料？");
         map.put("answer", "根据个人情况不同，实际所提供的材料也不相同。" +
                 "\n本市户口：需要携带本人身份证原件、身份证复印件1张、白底一寸彩照2张。" +
                 "\n外市户口：需要在考驾照当地办理居住证，然后携带您的居住证和身份证原件、身份证复印件1张、白底一寸彩照2张");
         list.add(map);

         map = new HashMap<String, Object>();
         map.put("problem", "全额学费都包括哪些费用？我还需要交其他费用吗？");
         map.put("answer", "天保驾校实行一费制收费。根据个人情况和班级不同，可能会有考前模拟费、补考费等额外费用支出。");
         list.add(map);

         map = new HashMap<String, Object>();
         map.put("problem", "报名成功以后，什么时候可以开始学车呢？");
         map.put("answer", "根据班型不同，上车时间也不同。最快1天就能上车，最慢15-20天。实际以驾校的通知为准");
         list.add(map);

         map = new HashMap<String, Object>();
         map.put("problem", "报考成功之后，一般多久拿到驾照？");
         map.put("answer", "正常情况下45天拿证。根据个人情况和班型不同，学习和考试的通过情况不同，拿证的时间也会有前后差异");
         list.add(map);


     }
    private void initview() {

        summonerList = (ListView)findViewById(R.id.listView);
        summonerList.setAdapter(new ListViewAdapter(list,ProblemActivity.this));

        back = ((ImageView) findViewById(R.id.iv_back));
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
