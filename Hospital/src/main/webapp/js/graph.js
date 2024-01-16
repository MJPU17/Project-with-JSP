var nodesData = [];

var linksData = [];
const graph = () => {
    return {
        title: {
            text: '',
        },
        tooltip: {},
        animationDurationUpdate: 1500,
        animationEasingUpdate: 'quinticInOut',
        series: [
            {
                type: 'graph',
                layout: 'none',
                symbolSize: 50,
                roam: true,
                label: {
                    show: true
                },
                force:{
                    repulsion: 50,
                    gravity: 0.1,  
                },
                edgeSymbol: ['circle', 'arrow'],
                edgeSymbolSize: [2, 10],
                edgeLabel: {
                    fontSize: 20
                },
                force: {
                    repulsion: 100, 
                    gravity: 0.1,  
                    edgeLength: 100 
                },
                data: nodesData,
                links: linksData,
                lineStyle: {
                    opacity: 0.9,
                    width: 2,
                    curveness: 0
                }
            }
        ]
    };

};
const initGraph = () => {
    const chart1 = echarts.init(document.getElementById("chartg"));

    chart1.setOption(graph());
    chart1.setOption({
        series: [
            {
                layout: 'force', // Usa el algoritmo de layout automático
                force: {
                    // Puede ajustar los parámetros según tus necesidades
                    repulsion: 100, // Fuerza de repulsión entre nodos
                    gravity: 0.01,  // Gravedad
                    edgeLength: 200 // Longitud ideal de las conexiones
                },
                data: nodesData,
                links: linksData
            }
        ]
    });

};

function agregarNodo(nodeName) {

    const nodoExistente = nodesData.find(node => node.name === nodeName);
    if (nodoExistente) {
        alert("El nodo ya existe.");
        return;
    }

    nodesData.push({ name: nodeName});

    initGraph();

};

function agregarConexion(edgeSource,edgeTarget) {

    const sourceNode = nodesData.find(node => node.name === edgeSource);
    const targetNode = nodesData.find(node => node.name === edgeTarget);

    if (!sourceNode || !targetNode) {
        alert("Nodos de origen o destino no válidos.");
        return;
    }
    linksData.push({ source: edgeSource, target: edgeTarget });


    initGraph();
}

window.addEventListener("load", () => {
    initGraph();
})