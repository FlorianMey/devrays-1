varying vec4 v_color;
varying vec2 v_texCoords;

uniform sampler2D u_texture;
uniform float u_runtime;
uniform vec2 u_resolution, u_screen_center;

const vec4 COLOR_RED = vec4(1.0, 0.0, 0.0, 1.0);

// NOISE EFFECT

const float NOISE_LINE_MOVEMENT_SPEED = 0.2;
const float NOISE_SHIFT_STRENGTH = 0.002;
const float NOISE_VARIATION_SPEED = 0.01;
const float NOISE_RANGE = 10.0;
const float NOISE_COLOR_FACTOR = 1.0;

// DAMAGE EFFECT

uniform float u_damage_angle;
uniform float u_damage_strength;

const float BRIGHTNESS_MIN = 1.0;
const float DAMAGE_SHIFT_STRENGTH = 0.1;
const float DAMAGE_NOISE_ABSTRACTION_FREQUENCY = 0.2;
const float DAMAGE_NOISE_ABSTRACTION_STRENGTH = 30.0;
const float DAMAGE_VIGNETTE_STRENGTH = 1.0;

// direction is angle in radians
vec2 move(vec2 point, float direction, float movementStrength)
{
	point.x -= sin(direction) * movementStrength;
	point.y -= cos(direction) * movementStrength;
    return point;
}

// calculates absolute distance between screen mid and specified point
float getDistanceToMid(vec2 point)
{
 	return sqrt(pow(u_screen_center.x - point.x, 2.0) + pow(u_screen_center.y - point.y, 2.0));   
}

// calculates distance between screen mid and specified point in relation to screen size, returns a value between 0 and 1
float getDistanceToMidNormalized(vec2 uv)
{
	return sqrt(pow(0.5 - uv.x, 2.0) + pow(0.5 - uv.y, 2.0));  
}

float getColorBrightNess(vec4 color)
{
	return (color.r + color.g + color.b) / 3.0;
}

void main(void)
{
	vec2 uv = gl_FragCoord.xy / u_resolution.xy;
	
	// NOISE EFFECT (a horizontal line is permanently moving through screen and shifts pixels that are near to this line)
	
	float distanceToNoiseLine = abs(gl_FragCoord.y - mod(u_runtime * NOISE_LINE_MOVEMENT_SPEED, u_resolution.y));

	if (distanceToNoiseLine < NOISE_RANGE) {
		float closenessToNoiseLine = NOISE_RANGE - distanceToNoiseLine;
		uv.x += closenessToNoiseLine * (NOISE_SHIFT_STRENGTH + sin(u_runtime * NOISE_VARIATION_SPEED) * NOISE_SHIFT_STRENGTH);
	}
		
	vec4 currentColor = texture2D(u_texture, uv);
	
	if (NOISE_COLOR_FACTOR != 1.0 && distanceToNoiseLine < NOISE_RANGE) {
		currentColor *= NOISE_COLOR_FACTOR;
	}
	
	// DAMAGE EFFECT
	
	if (u_damage_strength != 0.0) {
	
		float brightness = getColorBrightNess(currentColor);
		float midDistance = getDistanceToMidNormalized(uv);
	    float distanceNoiseFactor = abs(sin(midDistance * DAMAGE_NOISE_ABSTRACTION_FREQUENCY)) * midDistance * DAMAGE_NOISE_ABSTRACTION_STRENGTH;
	    
	    uv.xy = move(uv.xy, u_damage_angle + distanceNoiseFactor, u_damage_strength * DAMAGE_SHIFT_STRENGTH);
	 	currentColor = mix(currentColor, COLOR_RED, currentColor.a * brightness);
	    currentColor = texture2D(u_texture, uv);
	    
	    float damageVignetteFactor = midDistance * u_damage_strength * DAMAGE_VIGNETTE_STRENGTH;
	    currentColor.ar += damageVignetteFactor;
	    	    
	    if (damageVignetteFactor >= 1.0) {
	    	currentColor *= midDistance * u_damage_strength;
	    }
    }
    
    // FINISHING
    
	gl_FragColor = currentColor;
}