(
SynthDef.new(\bellosc, {
	arg freq = 450, amp = 0.5, release = 3;
	var env = EnvGen.kr(Env.new([1, 0], [release], \sqr), doneAction:2);
	var sig = SinOsc.ar(freq) * amp * env;
	Out.ar(0, sig);
	Out.ar(1, sig);
	}
).add;
)


(
SynthDef.new(\striker, {
	var senv = EnvGen.kr(Env.new([1, 0.01], [0.01], \exp), doneAction:2);
	var striker = WhiteNoise.ar() * senv;
	Out.ar(0, striker);
	Out.ar(1, striker);
	}
).add;
)

(

	var fund = 263;
	[[fund * 0.501, 0.002, 1.2], [fund * 1, 0.02, 1.2], [fund * 0.7, 0.001, 1.2],
	[fund * 2.002, 0.008, 0.9], [fund * 3, 0.02, 0.9], [fund * 9.6, 0.004, 0.9],
	[fund * 2.49, 0.02, 0.25], [fund * 11, 0.04, 0.25], [fund * 2.571, 0.02, 0.25],
	[fund * 3.05, 0.005, 0.14], [fund * 6.245, 0.05, 0.14], [fund * 12.49, 0.05, 0.14],
	[fund * 13, 0.02, 0.07], [fund * 16, 0.03, 0.07], [24 * fund, 0.04, 0.07]].do{
		arg i;
		Synth.new(\bellosc, [\freq, i[0], \amp, i[1], \release, i[2]]);
	};
	Synth.new(\striker);
)
