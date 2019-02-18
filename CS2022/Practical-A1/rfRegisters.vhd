library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity reg8_16bit is
    Port(	D: in std_logic_vector(15 downto 0);
			Q: out std_logic_vector(15 downto 0);
			load: in std_logic;
			clk: in std_logic);
end reg8_16bit;

architecture breg of reg8_16bit is 
signal memory: std_logic_vector(15 downto 0) := x"0000";
begin
    memory <= D after 5ns when load = '1' and clk='1';
    Q <= memory;
end breg;
