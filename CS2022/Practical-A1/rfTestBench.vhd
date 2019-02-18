library IEEE;
use IEEE.STD_LOGIC_1164.ALL;

entity rfTestBench is
end rfTestBench;

architecture rftb of rfTestBench is
    component registerFile is
        Port(   src_S0, src_S1, src_S2, des_A0, des_A1, des_A2, data_src: in std_logic;
                data: in std_logic_vector(15 downto 0);
                clk: inout std_logic; 
                reg0_out, reg1_out, reg2_out, reg3_out, reg4_out, reg5_out, reg6_out, reg7_out: out std_logic_vector(15 downto 0));
    end component;
    
    signal src_S0, src_S1, src_S2, des_A0, des_A1, des_A2, clk, data_src: std_logic;
    signal data: std_logic_vector(15 downto 0);
    signal reg0_out, reg1_out, reg2_out, reg3_out, reg4_out, reg5_out, reg6_out, reg7_out: std_logic_vector(15 downto 0);
    constant wt : time := 20 ns;
    begin
    
        rf0: registerFile port map(   src_S0 => src_S0, src_S1 => src_S1, src_S2 => src_S2, des_A0 => des_A0,
                            des_A1 => des_A1, des_A2 => des_A2, clk => clk, data_src => data_src,
                            data => data, reg0_out => reg0_out,reg1_out => reg1_out, 
                            reg2_out => reg2_out, reg3_out => reg3_out, reg4_out => reg4_out,
                            reg5_out => reg5_out, reg6_out => reg6_out, reg7_out => reg7_out);
        clock: process
            begin
                clk <= '0';
                wait for wt/2;
                clk <= '1';
                wait for wt/2;
            end process;    
                            
        tb: process
            begin
                --INIT INPUT
                src_S0 <= '0';
                src_S1 <= '0';
                src_S2 <= '0';
                data_src <= '0';
                wait for wt;
                --clk <= '1';
                
                --FILL REGISTERS
                
                --clk <='0';
                --wait for 5ns;
                data <= x"0012";
                des_A0 <= '0';
                des_A1 <= '0';
                des_A2 <= '0';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"0345";
                des_A0 <= '0';
                des_A1 <= '0';
                des_A2 <= '1';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"0678";
                des_A0 <= '0';
                des_A1 <= '1';
                des_A2 <= '0';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"4242";
                des_A0 <= '0';
                des_A1 <= '1';
                des_A2 <= '1';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"0cde";
                des_A0 <= '1';
                des_A1 <= '0';
                des_A2 <= '0';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"1012";
                des_A0 <= '1';
                des_A1 <= '0';
                des_A2 <= '1';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"1345";
                des_A0 <= '1';
                des_A1 <= '1';
                des_A2 <= '0';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                data <= x"1678";
                des_A0 <= '1';
                des_A1 <= '1';
                des_A2 <= '1';
                wait for wt;
                --clk <= '1';
                
                --wait for wt;
                
                --TEST IF REGISTERS ARE PROPERLY FILLED
                
                assert reg0_out = x"0012" report "reg0 WRONG" severity error;
                assert reg1_out = x"0345" report "reg1 WRONG" severity error;
                assert reg2_out = x"0678" report "reg2 WRONG" severity error;
                assert reg3_out = x"4242" report "reg3 WRONG" severity error;
                assert reg4_out = x"0cde" report "reg4 WRONG" severity error;
                assert reg5_out = x"1012" report "reg5 WRONG" severity error;
                assert reg6_out = x"1345" report "reg6 WRONG" severity error;
                assert reg7_out = x"1678" report "reg7 WRONG" severity error;
                
                wait for wt;
                
                --clk <= '0';
                --wait for 5ns;
                src_S0 <= '0';
                src_S1 <= '1';
                src_S2 <= '1';
                
                des_A0 <= '0';
                des_A1 <= '0';
                des_A2 <= '0';
                
                data_src <= '1';
                wait for wt;
                --clk <= '1';
                --wait for wt;
                --clk <= '0';
                
                assert reg0_out = x"4242" report "value of reg3 did not land in reg0." severity error;
                
                report "Done testing.";
                
                wait;
                
            end process;          
end rftb;
