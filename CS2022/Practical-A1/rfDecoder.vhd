library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity decoder is
	Port(	A0, A1, A2: in std_logic;
		Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7: out std_logic);
end decoder;

architecture bdec of decoder is
signal S: std_logic_vector(2 downto 0);
signal Z: std_logic_vector(7 downto 0);
begin
S(2) <= A0;
S(1) <= A1;
S(0) <= A2;

Z <=    "00000001" after 5ns when S = "000" else
        "00000010" after 5ns when S = "001" else
        "00000100" after 5ns when S = "010" else
        "00001000" after 5ns when S = "011" else
        "00010000" after 5ns when S = "100" else
        "00100000" after 5ns when S = "101" else
        "01000000" after 5ns when S = "110" else
        "10000000" after 5ns when S = "111";

Q0 <= Z(0) after 5ns;
Q1 <= Z(1);
Q2 <= Z(2);
Q3 <= Z(3);
Q4 <= Z(4);
Q5 <= Z(5);
Q6 <= Z(6);
Q7 <= Z(7);
end bdec;
