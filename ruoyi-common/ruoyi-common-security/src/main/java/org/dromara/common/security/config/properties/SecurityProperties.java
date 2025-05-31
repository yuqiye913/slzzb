package org.dromara.common.security.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Security 配置属性
 *
 * @author Lan Zhan
 */
@Data
@ConfigurationProperties(prefix = "security")
public class SecurityProperties {

    /**
     * 排除路径
     */
    private String[] excludes;


}
