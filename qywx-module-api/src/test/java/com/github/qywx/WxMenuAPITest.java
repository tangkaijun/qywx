package com.github.qywx;

import com.github.qywx.api.AccessTokenAPI;
import com.github.qywx.api.WxMenuAPI;
import com.github.qywx.core.request.menu.ComplexButton;
import com.github.qywx.core.request.menu.Menu;
import com.github.qywx.core.request.menu.SimpleButton;
import com.github.qywx.core.response.AccessToken;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by kaijun on 2017/12/11.
 */
public class WxMenuAPITest {

    @Test
    public void testCreateMenu() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        Menu menu = new Menu();
        SimpleButton btn11 = new SimpleButton();
        btn11.setName("菜单1-1");
        btn11.setType("click");
        btn11.setKey("11");
        SimpleButton btn12 = new SimpleButton();
        btn12.setName("菜单1-2");
        btn12.setType("click");
        btn12.setKey("12");
        SimpleButton btn13 = new SimpleButton();
        btn13.setName("菜单1-3");
        btn13.setType("click");
        btn13.setKey("13");
        SimpleButton btn14 = new SimpleButton();
        btn14.setName("菜单1-4");
        btn14.setType("click");
        btn14.setKey("14");
        SimpleButton btn15 = new SimpleButton();
        btn15.setName("菜单1-5");
        btn15.setType("click");
        btn15.setKey("15");

        SimpleButton btn21 = new SimpleButton();
        btn21.setName("菜单2-1");
        btn21.setType("click");
        btn21.setKey("21");
        SimpleButton btn22 = new SimpleButton();
        btn22.setName("菜单2-2");
        btn22.setType("click");
        btn22.setKey("22");
        SimpleButton btn23 = new SimpleButton();
        btn23.setName("菜单2-3");
        btn23.setType("click");
        btn23.setKey("23");
        SimpleButton btn24 = new SimpleButton();
        btn24.setName("菜单2-4");
        btn24.setType("click");
        btn24.setKey("24");
        SimpleButton btn25 = new SimpleButton();
        btn25.setName("菜单2-5");
        btn25.setType("click");
        btn25.setKey("25");

        SimpleButton btn31 = new SimpleButton();
        btn31.setName("菜单Url3-1");
        btn31.setType("view");
        btn31.setUrl("http://www.baidu.com");
        SimpleButton btn32 = new SimpleButton();
        btn32.setName("菜单3-2");
        btn32.setType("click");
        btn32.setKey("32");
        SimpleButton btn33 = new SimpleButton();
        btn33.setName("菜单3-3");
        btn33.setType("click");
        btn33.setKey("33");
        SimpleButton btn34 = new SimpleButton();
        btn34.setName("菜单3-4");
        btn34.setType("click");
        btn34.setKey("34");
        SimpleButton btn35 = new SimpleButton();
        btn35.setName("菜单3-5");
        btn35.setType("click");
        btn35.setKey("35");

        ComplexButton mainBtn1 = new ComplexButton();
        mainBtn1.setName("菜单1");
        mainBtn1.setSub_button(Arrays.asList(btn11, btn12, btn13, btn14, btn15));

        ComplexButton mainBtn2 = new ComplexButton();
        mainBtn2.setName("菜单2");
        mainBtn2.setSub_button(Arrays.asList(btn21, btn22, btn23, btn24, btn25));

        ComplexButton mainBtn3 = new ComplexButton();
        mainBtn3.setName("菜单3");
        mainBtn3.setSub_button(Arrays.asList(btn31, btn32, btn33, btn34, btn35));

        menu.setButton(Arrays.asList(mainBtn1, mainBtn2, mainBtn3));
        WxMenuAPI.createMenu(menu,1000002,accessToken.getAccess_token());
    }

    @Test
    public void testGetMenu() throws Exception{
        AccessToken accessToken = AccessTokenAPI.getAccessToken("wwc8cefdb9d53fd9f0","08IOCnadzFkypgz09nrmuQBMudmPT9PiNERaJimXXck");
        //Menu menu = WxMenuAPI.getMenu(22,accessToken.getAccess_token());
    }
}
