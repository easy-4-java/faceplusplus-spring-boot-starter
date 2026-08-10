package com.faceplusplus.spring.boot;

/**
 * Constants for the Face++ / Agora integration.
 *
 * @author <a href="https://github.com/loong10k">Loong Wan</a>
 * @since 1.0.0
 */public interface FaceppConstant {

    // Get all users in a channel.
    // https://api.agora.io/dev/v1/channel/user/{appid}/{channelName}
    String URL_CHANNEL_USER = "https://api.agora.io/dev/v1/channel/user/{0}/{1}";

    // Ban a user.
    // https://api.agora.io/dev/v1/kicking-rule
    String URL_RULE = "https://api.agora.io/dev/v1/kicking-rule";

    // Recording request uid.
    String RECORDING_UID = "10";

    // Storage path: /video/{{yyyy-MM-dd}}.
    String VEIDO_PAHT = "video";
}
