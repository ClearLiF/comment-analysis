package com.cuit.service;

import com.baidu.aip.nlp.AipNlp;
import com.cuit.mapper.CommentMapper;
import com.cuit.model.Comment;
import com.cuit.model.CommentExample;
import com.cuit.util.JSONUtil;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author : CLEAR Li
 * @version : V1.0
 * @className : CommentService
 * @packageName : com.cuit.service
 * @description : 一般类
 * @date : 2020-05-29 16:45
 **/
@Service
@Transactional
public class CommentService {
    //设置APPID/AK/SK
    //@Value("${APP_ID}")
    public  String APP_ID = "19934789";
   // @Value("${API_KEY}")
    public  String API_KEY= "GC4wtv9uKrFHYn3SLqbNtsAF";
   // @Value("${SECRET_KEY}")
    public  String SECRET_KEY= "QwiGRDL6FlC4tayCsmqlxDcWeeC0eGdE";

    private CommentMapper commentMapper;
    @Autowired
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    /**
     * 无建议(默认)
     * @description
     *          从数据库中读取相应的数据并进行分词操作
     * @author ClearLi
     * @date 2020/5/29 16:52
     * @param
     * @return java.lang.Integer
     */
    public Integer partingWord(){
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria();
        //获取所有的评论信息
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        for (Comment comment : comments) {
            //进行分词
           // String words = JSONUtil.getWords(comment);
           // List<String> words = JSONUtil.getWords(getPartingWord(comment.getContent()));
            String partingWord = getPartingWord(comment);
            System.out.println(partingWord);
            //写入数据库++++date 2020年5月29日
        }
        return 0;
    }

    public String getPartingWord(Comment comment){
        // 初始化一个AipNlp
        AipNlp client = new AipNlp(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        //client.setConnectionTimeoutInMillis(2000);
        //client.setSocketTimeoutInMillis(60000);

        // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
        // client.setHttpProxy("https://aip.baidubce.com/rpc/2.0/nlp/v2/word_emb_vec", 8888);  // 设置http代理
        // client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理
        // 调用接口
        JSONObject res;
        if (comment.getContent() != null) {
            System.out.println("res = " + comment.getContent());
             res = client.lexer(comment.getContent(), null);
            //List<String> words = JSONUtil.getWords(res.toString(2));
            return JSONUtil.getWords(comment, res.toString(2));
        }
     return null;
    }
}
