package hello.jdbc.exception.translator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

import hello.jdbc.connection.ConnectionConst;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SpringExceptionTranslatorTest {
    
    DataSource dataSource;

    @BeforeEach
    void init(){
        dataSource = new DriverManagerDataSource(ConnectionConst.URL, ConnectionConst.USERNAME, ConnectionConst.PASSOWRD);
    }

    @Test
    void sqlExceptionErrorCOde(){
        String sql = "select bad grammar";

        try{
            Connection con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
        } catch(SQLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);
            int errorCode = e.getErrorCode();
            log.info("errocod = {}", errorCode);
            log.info("error", e);
        }
    }

    @Test
    void exceptionTranslator(){
        String sql = "select bad grammer";
        try{
            Connection con = dataSource.getConnection();
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.executeQuery();
        } catch(SQLException e){
            Assertions.assertThat(e.getErrorCode()).isEqualTo(42122);
            
            SQLErrorCodeSQLExceptionTranslator exceptionTranslator = new SQLErrorCodeSQLExceptionTranslator(dataSource);
            DataAccessException resultEx = exceptionTranslator.translate("select", sql, e);
            log.info("resultEx", resultEx);
            Assertions.assertThat(resultEx.getClass()).isEqualTo(BadSqlGrammarException.class);
        }
    }
}
