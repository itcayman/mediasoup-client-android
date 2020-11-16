package org.webrtc;

import java.lang.reflect.Field;

public class RTCUtils {

  public static long getNativeMediaStreamTrack(MediaStreamTrack track) {
    return track != null ? track.getNativeMediaStreamTrack() : 0L;
  }

  public static long getNativeRtpSender(RtpSender sender) {
    return sender != null ? sender.getNativeRtpSender() : 0L;
  }

  // use Reflection to get RtpReceiver#nativeRtpReceiver
  public static long getNativeRtpReceiver(RtpReceiver receiver) {
    long nativeRtpReceiver = 0L;
    try {
      Class<?> clazz = receiver.getClass();
      Field f = clazz.getDeclaredField("nativeRtpReceiver");
      f.setAccessible(true);
      nativeRtpReceiver = (long) f.get(receiver);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    }
    return nativeRtpReceiver;
  }

  public static RtpParameters.Encoding genRtpEncodingParameters(
      String rid,
      boolean active,
      Double scaleResolutionDownBy) {
    return new RtpParameters.Encoding(
        rid,
        active,
        scaleResolutionDownBy);
  }

  public static MediaStreamTrack createMediaStreamTrack(long nativeTrack) {
    return MediaStreamTrack.createMediaStreamTrack(nativeTrack);
  }
}
