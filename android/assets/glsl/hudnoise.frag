varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_runtime;
uniform vec2 u_resolution;

const float MOVEMENT_SPEED = 0.15;
const float SHIFT_STRENGTH = 500.0;
const float VARIATION_SPEED = 0.01;
const float RANGE = 5.0;
const float LIGHT_STRENGTH = 2.0;

void main(void)
{
	vec2 uv = gl_FragCoord.xy / u_resolution.xy;
	float cursorDistanceY = abs(gl_FragCoord.y - mod(u_runtime * MOVEMENT_SPEED, u_resolution.y));

	if (cursorDistanceY < RANGE) {
		float cursorClosenessY = RANGE - cursorDistanceY;
		uv.x += cursorClosenessY / (SHIFT_STRENGTH + sin(u_runtime * VARIATION_SPEED) * SHIFT_STRENGTH * 0.5);
	}
		
	vec4 finalColor = texture2D(u_texture, uv);
	
	if (cursorDistanceY < RANGE) {
		float actualStrength = LIGHT_STRENGTH + sin(u_runtime * VARIATION_SPEED) * 0.8;
		finalColor *= vec4(actualStrength, actualStrength, actualStrength, actualStrength);
	}
	
	gl_FragColor = finalColor;
}