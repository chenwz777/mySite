package com.chenwz.mysite.service.impl;

import com.chenwz.mysite.service.DoubleColorBallService;
import com.chenwz.mysite.vo.BallReturnDTO;
import lombok.extern.slf4j.Slf4j;
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
            BallReturnDTO returnDTO = this.generateDoubleBallByRandom();
            resultList.add(returnDTO);
        }

        return resultList;
    }

    private void generateDoubleBallByShuffle() {
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
        redList.forEach(p->{
            if (p < 10) {
                System.out.print("0" + p);
            } else {
                System.out.print(p);
            }
            System.out.print(" ");
        });
        int blue = blueAllList.get(0);
        if (blue < 10) {
            System.out.print("[0" + blue + "]");
        } else {
            System.out.print("[" + blue + "]");
        }
        //校验结果
        this.check(redList, blue);
        System.out.println();
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
        redList.forEach(p -> {
            if (p < 10) {
                System.out.print("0" + p);
            } else {
                System.out.print(p);
            }
            System.out.print(" ");
        });

        if (blue < 10) {
            System.out.print("[0" + blue + "]");
        } else {
            System.out.print("[" + blue + "]");
        }
        //校验结果
        this.check(redList, blue);
        System.out.println();
        return null;
    }

    /**
     * 校验一组号码是否符合要求
     *
     *  1.三个及以上连续的不要，概率极低
     *  2.全大，全小的不要，概率很小，尽量保证2/4或者33比例， 1/5都很少，也不要了
     *  3.单双，只要不是6单/6双都可以接受
     *  4.红球3区，每个区至少有1球
     *
     */
    private void check(List<Integer> redList, Integer blue){
        int bigCount = 0;  //大小，如果是0，1 5，6 不满足大小
        int singleCount = 0;  //单双，如果是0或者6，不满足
        int r1 = 0, r2 = 0, r3 = 0;  //红球三区 1-11,12-22,23-33
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
                        System.out.print(" 连续不符合，序列为：" + shunList);
                        return;
                    }

                }
            }
        }
        if(shunList.size() >=3){
            System.out.print(" 连续不符合，序列为：" + shunList);
            return;
        }
        if(bigCount< 2 || bigCount > 4){
            //不符合
            System.out.print(" 大小不符合：大号有("+bigCount+")个,小号有("+(6-bigCount)+")个");
            return;
        }
        if(singleCount == 0 || singleCount == 6){
            System.out.print(" 单双不符合：单号有("+singleCount+")个,双号有("+(6-singleCount)+")个");
            return;
        }
        if(r1==0 || r2==0 || r3==0){
            System.out.print(" 三区不符合：一区有("+r1+")个,二区有("+r2+")个,三区有("+r3+")个");
            return;
        }

        System.out.print(" 符合");
    }
}
