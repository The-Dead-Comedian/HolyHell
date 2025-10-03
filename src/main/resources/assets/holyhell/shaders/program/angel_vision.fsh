#version 150

uniform sampler2D DiffuseSampler;

in vec2 texCoord;
in vec2 oneTexel;

uniform vec2 InSize;

uniform vec3 Gray;
uniform vec3 RedMatrix;
uniform vec3 GreenMatrix;
uniform vec3 BlueMatrix;
uniform vec3 Offset;
uniform vec3 ColorScale;
uniform float Saturation;
uniform float Follly;

out vec4 fragColor;

vec3 rgb2hsv(vec3 c) {
    vec4 K = vec4(0.0, -1.0 / 3.0, 2.0 / 3.0, -1.0);
    vec4 p = c.g < c.b ? vec4(c.bg, K.wz) : vec4(c.gb, K.xy);
    vec4 q = c.r < p.x ? vec4(p.xyw, c.r) : vec4(c.r, p.yzx);

    float d = q.x - min(q.w, q.y);
    float e = 1.0e-10;
    return vec3(abs(q.z + (q.w - q.y) / (6.0 * d + e)), d / (q.x + e), q.x);
}

vec3 hsv2rgb(vec3 c) {
    vec4 K = vec4(1.0, 2.0 / 3.0, 1.0 / 3.0, 3.0);
    vec3 p = abs(fract(c.xxx + K.xyz) * 6.0 - K.www);
    return c.z * mix(K.xxx, clamp(p - K.xxx, 0.0, 1.0), c.y);
}

bool isTargetColor(vec3 hsv) {
    return (
    // ff0035 (Hostile)
    (hsv.x > 0.95 || hsv.x < 0.02) && hsv.y > 0.8 && hsv.z > 0.6 ||

//    // 00a4ff (Boss)z
    (hsv.x > 0.53 && hsv.x < 0.58) && hsv.y > 0.8 && hsv.z > 0.6 ||

    // 00ff7e (Peaceful)
    (hsv.x > 0.40 && hsv.x < 0.45) && hsv.y > 0.8 && hsv.z > 0.6 ||

    // c000ff (Player)
    (hsv.x > 0.75 && hsv.x < 0.82) && hsv.y > 0.8 && hsv.z > 0.6
    );
}

void main() {
    vec4 InTexel = texture(DiffuseSampler, texCoord);

    // Color Matrix
    float RedValue = dot(InTexel.rgb, RedMatrix);
    float GreenValue = dot(InTexel.rgb, GreenMatrix);
    float BlueValue = dot(InTexel.rgb, BlueMatrix);
    vec3 OutColor = vec3(RedValue, GreenValue, BlueValue);

    // Offset & Scale
    OutColor = (OutColor * ColorScale) + Offset;

    // Saturation
    vec3 hsv = rgb2hsv(InTexel.rgb);

    if (!isTargetColor(hsv)) {
        float Luma = dot(OutColor, Gray);
        vec3 Chroma = OutColor - Luma;
        OutColor = ((Chroma * Saturation) + Luma);
    }
    // Folly color boost


    fragColor = vec4(OutColor, 1.0);
}