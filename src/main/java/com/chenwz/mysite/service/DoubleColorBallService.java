package com.chenwz.mysite.service;


import com.chenwz.mysite.vo.BallReturnDTO;

import java.util.List;

public interface DoubleColorBallService {


    List<BallReturnDTO> getBall(int number);
}
