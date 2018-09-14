-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: 15-Set-2018 às 00:30
-- Versão do servidor: 5.7.17
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `java`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `metodologia`
--

CREATE TABLE `metodologia` (
  `id` int(5) NOT NULL,
  `titulo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `metodologia`
--

INSERT INTO `metodologia` (`id`, `titulo`) VALUES
(1, 'Scrum'),
(2, 'Scrum'),
(3, 'A');

-- --------------------------------------------------------

--
-- Estrutura da tabela `pessoa`
--

CREATE TABLE `pessoa` (
  `id` int(5) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `email` varchar(150) NOT NULL,
  `sexo` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `pessoa`
--

INSERT INTO `pessoa` (`id`, `nome`, `email`, `sexo`) VALUES
(1, 'Richard', 'r@', 'masculino'),
(2, 'Jeferson', 'j@', 'masculino'),
(3, 'Nelson', 'n@', 'masculino'),
(4, 'Vinicius', 'v@', 'masculino');

-- --------------------------------------------------------

--
-- Estrutura da tabela `rel_tarefa_pessoa`
--

CREATE TABLE `rel_tarefa_pessoa` (
  `id` int(5) NOT NULL,
  `id_tarefa` int(11) NOT NULL,
  `id_pessoa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `rel_tarefa_pessoa`
--

INSERT INTO `rel_tarefa_pessoa` (`id`, `id_tarefa`, `id_pessoa`) VALUES
(1, 1, 1),
(2, 2, 1),
(3, 2, 2),
(4, 3, 4);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tarefa`
--

CREATE TABLE `tarefa` (
  `id` int(5) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `prazo_estimado` date DEFAULT NULL,
  `descricao` varchar(400) DEFAULT NULL,
  `data_inicio` date NOT NULL,
  `data_termino` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tarefa`
--

INSERT INTO `tarefa` (`id`, `titulo`, `prazo_estimado`, `descricao`, `data_inicio`, `data_termino`) VALUES
(1, 'Atividade PC', '2018-09-14', 'Relacionamento com banco', '2018-08-10', '2018-09-07'),
(2, 'Atividade TLBD', '2018-09-18', 'View', '2018-09-10', '2018-09-10'),
(3, 'Atividade RCD', '2018-08-20', 'Rede da Etec', '2018-08-20', '2018-08-20');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `metodologia`
--
ALTER TABLE `metodologia`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pessoa`
--
ALTER TABLE `pessoa`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `rel_tarefa_pessoa`
--
ALTER TABLE `rel_tarefa_pessoa`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_Tarefa` (`id_tarefa`),
  ADD KEY `FK_Pessoa` (`id_pessoa`);

--
-- Indexes for table `tarefa`
--
ALTER TABLE `tarefa`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `metodologia`
--
ALTER TABLE `metodologia`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `pessoa`
--
ALTER TABLE `pessoa`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `rel_tarefa_pessoa`
--
ALTER TABLE `rel_tarefa_pessoa`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `tarefa`
--
ALTER TABLE `tarefa`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `metodologia`
--
ALTER TABLE `metodologia`
  ADD CONSTRAINT `FK_idTarefa` FOREIGN KEY (`id`) REFERENCES `tarefa` (`id`);

--
-- Limitadores para a tabela `rel_tarefa_pessoa`
--
ALTER TABLE `rel_tarefa_pessoa`
  ADD CONSTRAINT `FK_Pessoa` FOREIGN KEY (`id_pessoa`) REFERENCES `pessoa` (`id`),
  ADD CONSTRAINT `FK_Tarefa` FOREIGN KEY (`id_tarefa`) REFERENCES `tarefa` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
