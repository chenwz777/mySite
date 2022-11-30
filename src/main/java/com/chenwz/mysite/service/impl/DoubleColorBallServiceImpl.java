package com.chenwz.mysite.service.impl;

import com.chenwz.mysite.service.DoubleColorBallService;
import com.chenwz.mysite.vo.BallReturnDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class DoubleColorBallServiceImpl implements DoubleColorBallService {

    @Override
    public List<BallReturnDTO> getBall(int number) {
        //
        List<BallReturnDTO> resultList = new ArrayList<>(number);
        for(int i = 0; i < number; i++){
            BallReturnDTO returnRandomDTO = this.generateDoubleBallByRandom();
            resultList.add(returnRandomDTO);
            BallReturnDTO returnShuffleDTO = this.generateDoubleBallByShuffle();
            resultList.add(returnShuffleDTO);
        }
        return resultList;
    }

    private BallReturnDTO generateDoubleBallByShuffle() {
        int redMax = 33;
        int blueMax = 16;
        List<Integer> redAllList = new ArrayList();
        List<Integer> redList = new ArrayList<>();
        List<Integer> blueAllList = new ArrayList();
        for(int i = 1; i<= redMax; i++){
            redAllList.add(i);
        }
        for(int i = 1; i<= blueMax; i++){
            blueAllList.add(i);
        }
        Collections.shuffle(redAllList);
        Collections.shuffle(blueAllList);
        redList.addAll(redAllList.subList(0, 6));
        Collections.sort(redList);
        final String[] info = {""};
        redList.forEach(p->{
            if (p < 10) {
                info[0] += "0" + p;
            } else {
                info[0] += p;
            }
            info[0]+=" ";
        });
        int blue = blueAllList.get(0);
        if (blue < 10) {
            info[0] += "[0" + blue + "]";
        } else {
            info[0] += "[" + blue + "]";
        }
        //校验结果
        String message = this.check(redList, blue);
        log.info(info[0] + " " + (StringUtils.isEmpty(message)? "符合": message));
        return BallReturnDTO.builder()
                .blue(blue)
                .type(2)
                .isLegal(StringUtils.isEmpty(message))
                .message(message)
                .redList(redList)
                .build();
    }

    private BallReturnDTO generateDoubleBallByRandom() {
        int redMax = 33;
        int blueMax = 16;
        List<Integer> redList = new ArrayList();
        int blue;
        Random random = ThreadLocalRandom.current();
        while (redList.size() < 6) {
            int tempRed = random.nextInt(redMax) + 1;
            if (!redList.contains(tempRed)) {
                redList.add(tempRed);
            }
        }
        blue = random.nextInt(blueMax) + 1;
        //排序
        Collections.sort(redList);
        //输出结果
        final String[] info = {""};
        redList.forEach(p->{
            if (p < 10) {
                info[0] += "0" + p;
            } else {
                info[0] += p;
            }
            info[0]+=" ";
        });
        if (blue < 10) {
            info[0] += "[0" + blue + "]";
        } else {
            info[0] += "[" + blue + "]";
        }
        //校验结果
        String message = this.check(redList, blue);
        log.info(info[0]);
        return BallReturnDTO.builder()
                .blue(blue)
                .type(1)
                .isLegal(StringUtils.isEmpty(message))
                .message(message)
                .redList(redList)
                .build();
    }

    /**
     * 校验一组号码是否符合要求
     *
     *  1.三个及以上连续的不要，概率极低
     *  2.全大，全小的不要，概率很小，尽量保证2/4或者33比例， 1/5都很少，也不要了
     *  3.单双，只要不是6单/6双都可以接受
     *  4.红球3区，每个区至少有1球，114情况也很少，暂且不要
     *
     */
    private String check(List<Integer> redList, Integer blue){
        int bigCount = 0;  //大小，如果是0，1 5，6 不满足大小
        int singleCount = 0;  //单双，如果是0或者6，不满足
        int r1 = 0, r2 = 0, r3 = 0;  //红球三区 1-11,12-22,23-33
        String info = null;
        List<Integer> shunList = new ArrayList<>();  //记下标
        for(int i = 0; i < redList.size(); i++){
            int redNum = redList.get(i);
            if(redNum > 16){
                bigCount++;
            }
            if((redNum & 1) == 1){
                singleCount++;
            }
            if(redNum<=11){
                r1++;
            } else if(redNum <=22){
                r2++;
            } else{
                r3++;
            }
            shunList.add(redNum);
            if(shunList.size() > 1){
                if(redNum - 1 != shunList.get(shunList.size()-2)){
                    if(shunList.size() <=3){
                        shunList.clear();
                        shunList.add(redNum);
                    } else {
                        //输出符合条件的，重置
                        shunList.remove(shunList.size()-1);
                        info = "连续不符合，序列为：" + shunList;
                        return info;
                    }

                }
            }
        }
        if(shunList.size() >=3){
            info = "连续不符合，序列为：" + shunList;
            return info;
        }
        if(bigCount< 2 || bigCount > 4){
            //不符合
            info = "大小不符合：大号有("+bigCount+")个,小号有("+(6-bigCount)+")个";
            return info;
        }
        if(singleCount == 0 || singleCount == 6){
            info = "单双不符合：单号有("+singleCount+")个,双号有("+(6-singleCount)+")个";
            return info;
        }
        if(r1==0 || r2==0 || r3==0){
            info = "三区不符合：一区有("+r1+")个,二区有("+r2+")个,三区有("+r3+")个";
            return info;
        }
        if((r1==1 && r2==1) || (r1==1 && r3== 1) || (r2==1 && r3==1)){
            info = "三区不符合：一区有("+r1+")个,二区有("+r2+")个,三区有("+r3+")个";
            return info;
        }

        return info;
    }
}
