package netty.session;

import lombok.Data;

/**
 * @author gqw
 * @mail gqiwei163@.com
 * @description
 * @date 2020-01-13
 */
@Data
public class Session {
    private String userId;
    private String userName;

    public Session(){}
    public Session(String userId,String userName){
        this.userId=userId;
        this.userName=userName;
    }
}
