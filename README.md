# Coding DOJO: File Packaging

## Aufgabenstellung:

- Input ist ein Ordner im Filesystem
- ein Inhaltsverzeichnis des Ordners soll erstellt werden, das alle Dateien mit Dateigröße und Hash enthält (kompletter Sub-Baum)
- der Ordner soll komprimiert werden (.zip, .tar.gz, .tar.xz, tar.bz2, oder was gefällt) in ein Archiv
- abschließend soll noch vom Archiv eine Checksumme berechnet werden

## Beispiel

Die `create-input.sh` erzeugt einen Ordner `input`, der eine exemplarische Ordnerstruktur mit ca 96MB Daten erzeugt.

Die Ordner-Struktur (exemplarisch):

```
├── binaries/               <dir>
│   ├── random/             <dir>
│   │   ├── 1               16MB
│   │   ├── 2               16MB
│   │   ├── 3               16MB
│   │   ├── 4               16MB
│   │   └── 5               16MB
│   └── zeros.bin           16MB
├── text/                   <dir>
│   ├── article.txt         5KB
│   ├── book.txt            20KB
│   └── epos.txt            50KB
└── pi.txt                  100KB
```

Erstellt werden soll eine Datei `output.zip` mit folgendem Inhalt

```
binaries/
binaries/zeros.bin
binaries/random/
binaries/random/1
binaries/random/4
binaries/random/3
binaries/random/2
binaries/random/5
pi.txt
text/
text/epos.txt
text/book.txt
text/article.txt
```

eine Datei `contents` mit in etwa folgendem Inhalt:

```
pi.txt                  100002     2165af0abeea7805ebbd90ed66e7b7a3c78ee6e1
binaries/zeros.bin      16777216   3b4417fc421cee30a9ad0fd9319220a8dae32da2
binaries/random/1       16777216   5780b009b98cf9c6c36650128a1c90cb78d6a884
binaries/random/4       16777216   c389d7e69234d6a7dafd45a0811f784c2187cd0a
binaries/random/3       16777216   2ac80fca447b4e83ed6e0703e9ee783552381079
binaries/random/2       16777216   cc294b610eb002866d4189f5665b2296e502e14f
binaries/random/5       16777216   1009621d805ebeb9d7119aec8635c0fa6dc0a337
text/epos.txt           47681      00d3d3d8d4bd25f5aafe03328b92bef95b4458b2
text/book.txt           20945      ee5eef64a7986d0beda93a9a6632c5a23623a59c
text/article.txt        5169       bdc17ccfc8db55af9121224dfc99e3bd1c2e13a2
```

und eine Datei output.tgz.sum:

```
47bf6e1a8da1db78d864db155f657731ea04cf28 output.tgz
```

## Erwartungshaltung

Die Aufgabe soll natürlich so effizient und resourcen-sparend wie möglich sein
