package com.chenwz.mysite.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chenwz.mysite.vo.user.UserQueryVO;
import com.chenwz.mysite.vo.user.UserReturnDTO;

public interface UserService {
    void getList();

    Page<UserReturnDTO> listPage(Integer pageSize, Integer pageNum, UserQueryVO userQueryVO);
}
