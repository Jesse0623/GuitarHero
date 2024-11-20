# GuitarHero
This project involves implementing two classes, GuitarString and Guitar37, to simulate a guitar using the Karplus-Strong algorithm for sound synthesis. By modeling string vibrations through a ring buffer and leveraging utility classes like StdAudio and StdDraw, the program allows users to pluck virtual guitar strings, producing realistic sound based on physical principles of string oscillation.

When a guitar string is plucked, the string vibrates and creates sound. The length of the string determines its fundamental frequency of vibration. We model a guitar string by sampling its displacement (a real number between -1/2 and +1/2) at N equally spaced points (in time), where N equals the sampling rate (44,100) divided by the fundamental frequency (rounded to the nearest integer). We store these displacement values in a structure that we will refer to as a ring buffer.

# Plucking the string
The excitation of the string can contain energy at any frequency. We simulate the excitation by filling the ring buffer with white noise: set each of the N sample displacements to a random real number between -1/2 and +1/2.

# The resulting vibrations
After the string is plucked, the string vibrates. The pluck causes a displacement which spreads wave-like over
time. The Karplus-Strong algorithm simulates this vibration by maintaining a ring buffer of the N samples: for each step the algorithm deletes the first sample from the ring buffer and adds to the end of the ring buffer the average of the first two samples, scaled by an energy decay factor of 0.996.

# Why it works
The two primary components that make the Karplus-Strong algorithm work are the ring buffer feedback
mechanism and the averaging operation.

The ring buffer feedback mechanism: The ring buffer models the medium (a string tied down at both
ends) in which the energy travels back and forth. The length of the ring buffer determines the
fundamental frequency of the resulting sound. Sonically, the feedback mechanism reinforces only the
fundamental frequency and its harmonics (frequencies at integer multiples of the fundamental). The
energy decay factor (.996 in this case) models the slight dissipation in energy as the wave makes a
roundtrip through the string.

The averaging operation: The averaging operation serves as a gentle low pass filter (which removes
higher frequencies while allowing lower frequencies to pass, hence the name). Because it is in the path of the feedback, this has the effect of gradually attenuating the higher harmonics while keeping the lower ones, which corresponds closely with how actually plucked strings sound.
