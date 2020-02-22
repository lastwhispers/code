package cn.lastwhisper.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

//SpringµÄÈÝÆ÷²»É¨Ãècontroller;¸¸ÈÝÆ÷
@ComponentScan(value="cn.lastwhisper",excludeFilters={
		@Filter(type=FilterType.ANNOTATION,classes={Controller.class})
})
public class RootConfig {

}
